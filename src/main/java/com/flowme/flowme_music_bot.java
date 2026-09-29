package com.flowme;

import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class flowme_music_bot implements LongPollingSingleThreadUpdateConsumer {
    // 1. Создаем переменную для клиента отправки сообщений
    private final TelegramClient telegramClient;

    // 2. Создаем конструктор, который требует эта ошибка!
    public flowme_music_bot(TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
    }

    @Override
    public void consume(Update update) {
        // Проверяем, что пришло текстовое сообщение
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            // Создаем ответ
            SendMessage message = SendMessage.builder()
                    .chatId(chatId)
                    .text("Вы написали: " + messageText)
                    .build();

            try {
                // Отправляем сообщение обратно пользователю
                telegramClient.execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}