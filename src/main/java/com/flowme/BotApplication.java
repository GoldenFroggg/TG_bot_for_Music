package com.flowme;

import com.flowme.telegram.CommandRouter;
import com.flowme.telegram.FlowmeMusicBot;
import com.flowme.telegram.command.*;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BotApplication {

    public static void main(String[] args) {
        String botToken = loadToken(args);
        if (botToken == null || botToken.isBlank()) {
            System.err.println("Токен не найден: задайте BOT_TOKEN, аргумент запуска "
                    + "или bot.token в application.yml");
            return;
        }

        try (TelegramBotsLongPollingApplication botsApplication =
                     new TelegramBotsLongPollingApplication()) {

            TelegramClient telegramClient = new OkHttpTelegramClient(botToken);

            List<CommandHandler> handlers = new ArrayList<>();
            handlers.add(new StartCommand());
            handlers.add(new HelpCommand(handlers));
            handlers.add(new AuthorCommand());
            handlers.add(new AboutCommand());
            CommandRouter router = new CommandRouter(handlers);

            botsApplication.registerBot(botToken, new FlowmeMusicBot(telegramClient, router));
            System.out.println("Бот успешно запущен и готов к работе!");

            Thread.currentThread().join();
        } catch (Exception e) {
            System.err.println("Ошибка при запуске бота: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /** Порядок поиска: переменная окружения, аргумент запуска, application.yml. */
    private static String loadToken(String[] args) {
        String token = System.getenv("BOT_TOKEN");
        if ((token == null || token.isBlank()) && args.length > 0) {
            token = args[0];
        }
        if (token == null || token.isBlank()) {
            token = readTokenFromResources();
        }
        return token == null ? null : token.trim();
    }

    private static String readTokenFromResources() {
        try (InputStream in = BotApplication.class.getClassLoader()
                .getResourceAsStream("application.yml")) {
            if (in == null) {
                return null;
            }
            Map<String, Object> root = new Yaml().load(in);
            if (root != null && root.get("bot") instanceof Map<?, ?> bot) {
                Object token = bot.get("token");
                return token == null ? null : token.toString();
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}