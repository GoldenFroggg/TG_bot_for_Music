package com.flowme;

import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class flowme_music_bot implements LongPollingSingleThreadUpdateConsumer {

    private final TelegramClient telegramClient;

    public flowme_music_bot(TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
    }

    @Override
    public void consume(Update update) {
        // Проверяем, что пришло текстовое сообщение
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            String answer;

            // Разбираем команду и готовим ответ
            switch (messageText) {
                case "/start":
                    answer = "Привет! Я бот FlowMe Music 🎵\n" +
                             "Напиши /help, чтобы увидеть, что я умею.";
                    break;

                case "/help":
                    answer = "Доступные команды:\n" +
                             "/help — показать этот список\n" +
                             "/about — о боте\n" +
                             "/author — об авторе";
                    break;

                case "/about":
                    answer = "FlowMe Music Bot — бот для работы с музыкой.\n" +
                             "Версия 0.1";
                    break;

                case "/author":
                    answer = "Автор бота: FlowMe team 💙";
                    break;

                default:
                    answer = "Не знаю такой команды 🤔 Напиши /help.";
                    break;
            }

            SendMessage message = SendMessage.builder()
                    .chatId(chatId)
                    .text(answer)
                    .build();

            try {
                telegramClient.execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
