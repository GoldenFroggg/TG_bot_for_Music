package com.flowme;

import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class BotApplication {
    public static void main(String[] args) {
        // 1. Вставьте сюда ваш токен от @BotFather (обязательно в кавычках)
        String botToken = "8915501494:AAEsuEUb1vEQ6JAdxwFvzFQfLyKRW_4EkzY";

        try (TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {
            // 2. Создаем HTTP-клиент для отправки сообщений
            TelegramClient telegramClient = new OkHttpTelegramClient(botToken);

            // 3. Регистрируем нашего бота и его логику ответов
            botsApplication.registerBot(botToken, new flowme_music_bot(telegramClient));
            System.out.println("Бот успешно запущен и готов к работе!");

            // 4. Удерживаем программу запущенной, чтобы она не закрылась сразу
            Thread.currentThread().join();
        } catch (Exception e) {
            System.err.println("Ошибка при запуске бота: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
