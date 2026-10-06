package com.flowme.telegram.command;

import com.flowme.telegram.model.BotReply;
import com.flowme.telegram.model.IncomingMessage;

public interface CommandHandler {

    String name();          // "/help"

    String description();   // показывается в /help

    BotReply handle(IncomingMessage message);
}
