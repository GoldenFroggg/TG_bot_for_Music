package com.flowme.telegram.command;

import com.flowme.telegram.model.BotReply;
import com.flowme.telegram.model.IncomingMessage;

import java.util.stream.Collectors;

public class AuthorCommand extends AbstractCommand {
    public AuthorCommand() {
        super("/author", "авторы бота ");
    }
    @Override
    protected BotReply execute(IncomingMessage message) {
        return BotReply.text("Авторы бота: крутые челы");
    }
}
