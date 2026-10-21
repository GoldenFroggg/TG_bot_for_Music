package com.flowme.telegram.command;

import com.flowme.telegram.model.BotReply;
import com.flowme.telegram.model.IncomingMessage;

public interface CommandHandler {

    String name();          // "/help"

    String description();

    BotReply handle(IncomingMessage message);
}
