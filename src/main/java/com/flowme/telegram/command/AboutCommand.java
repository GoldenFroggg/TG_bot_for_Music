package com.flowme.telegram.command;

import com.flowme.telegram.model.BotReply;
import com.flowme.telegram.model.IncomingMessage;

public class AboutCommand extends AbstractCommand {
    public AboutCommand() {
        super("/about", "О боте");
    }

    @Override
    protected BotReply execute(IncomingMessage message) {
        return new BotReply("Я музыкальный помощник FlowMe!");
    }
}