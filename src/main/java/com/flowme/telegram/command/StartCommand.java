package com.flowme.telegram.command;

import com.flowme.telegram.model.BotReply;
import com.flowme.telegram.model.IncomingMessage;
import org.springframework.stereotype.Component;

@Component
public class StartCommand extends AbstractCommand {

    public StartCommand() {
        super("/start", "Начать работу");
    }

    @Override
    protected BotReply execute(IncomingMessage message) {
        return BotReply.text("Привет! Я музыкальный помощник. Список команд: /help");
    }
}
