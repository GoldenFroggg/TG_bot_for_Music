package com.flowme.telegram.command;

import com.flowme.telegram.model.BotReply;
import com.flowme.telegram.model.IncomingMessage;

/** Шаблонный метод: общий порядок «проверить -> выполнить», детали в наследниках. */
public abstract class AbstractCommand implements CommandHandler {

    private final String name;
    private final String description;

    protected AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public final String name() {
        return name;
    }

    @Override
    public final String description() {
        return description;
    }

    @Override
    public final BotReply handle(IncomingMessage message) {
        validate(message);
        return execute(message);
    }

    protected void validate(IncomingMessage message) {
        // по умолчанию проверок нет
    }

    protected abstract BotReply execute(IncomingMessage message);
}
