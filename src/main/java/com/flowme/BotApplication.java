package com.flowme;
import com.flowme.telegram.CommandRouter;
import com.flowme.telegram.FlowmeMusicBot;
import com.flowme.telegram.command.*;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.ArrayList;
import java.util.List;

public class BotApplication {
    public static void main(String[] args) {


        System.out.println("BOT_TOKEN задан: " + (System.getenv("BOT_TOKEN") != null));
        String botToken = System.getenv("BOT_TOKEN");
        if ((botToken == null || botToken.isBlank()) && args.length > 0) {
            botToken = args[0];
        }
        if (botToken == null || botToken.isBlank()) {
            System.err.println("Не задана переменная окружения BOT_TOKEN");
            return;
        }

        try (TelegramBotsLongPollingApplication botsApplication =
                     new TelegramBotsLongPollingApplication()) {

            TelegramClient telegramClient = new OkHttpTelegramClient(botToken);

            List<CommandHandler> handlers = new ArrayList<>();
            // handlers.add(new StartCommand());  // добавьте ваши команды
            // handlers.add(new HelpCommand(handlers));
            CommandRouter router = new CommandRouter(handlers);

            botsApplication.registerBot(botToken, new FlowmeMusicBot(telegramClient, router));
            System.out.println("Бот успешно запущен и готов к работе!");

            Thread.currentThread().join();
        } catch (Exception e) {
            System.err.println("Ошибка при запуске бота: " + e.getMessage());
            e.printStackTrace();
        }
    }
}