package com.flowme.telegram.command;

import com.flowme.telegram.model.BotReply;
import com.flowme.telegram.model.IncomingMessage;

public class AuthorCommand extends AbstractCommand {
    public AuthorCommand() {
        super("/author", "Авторы бота");
    }

    @Override
    protected BotReply execute(IncomingMessage message) {
        return new BotReply("Авторы: Ванек и Тимурыч!");
    }
}