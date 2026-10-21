package com.flowme.config;

import com.flowme.telegram.FlowmeMusicBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Configuration
public class BotConfig {

    @Bean(destroyMethod = "close")
    public TelegramBotsLongPollingApplication botsApplication(
            @Value("${bot.token}") String token,
            FlowmeMusicBot bot) throws TelegramApiException {
        TelegramBotsLongPollingApplication app = new TelegramBotsLongPollingApplication();
        app.registerBot(token, bot);
        return app;
    }
}
