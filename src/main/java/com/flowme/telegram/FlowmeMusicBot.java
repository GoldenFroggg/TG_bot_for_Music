package com.flowme.telegram;

import com.flowme.telegram.model.BotReply;
import com.flowme.telegram.model.IncomingMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

/** Адаптер к Telegram: единственное место, где используются типы библиотеки. */
@Component
public class FlowmeMusicBot implements LongPollingSingleThreadUpdateConsumer {
    private static final Logger log = LoggerFactory.getLogger(FlowmeMusicBot.class);
    private final TelegramClient telegramClient;
    private final CommandRouter router;

    public FlowmeMusicBot(TelegramClient telegramClient, CommandRouter router) {
        this.telegramClient = telegramClient;
        this.router = router;
    }

    @Override
    public void consume(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }
        Message message = update.getMessage();
        String text = message.getText();
        if (!text.startsWith("/")) {
            return;
        }

        IncomingMessage incoming =
                IncomingMessage.from(message.getChatId(), message.getFrom().getId(), text);
        BotReply reply = router.route(incoming);
        send(message.getChatId(), reply.text());
    }

    private void send(long chatId, String text) {
        try {
            telegramClient.execute(SendMessage.builder().chatId(chatId).text(text).build());
        } catch (TelegramApiException e) {
            log.error("Не удалось отправить сообщение в чат {}", chatId, e);
        }
    }
}
