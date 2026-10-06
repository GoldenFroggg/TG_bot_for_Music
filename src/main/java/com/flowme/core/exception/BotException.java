package com.flowme.core.exception;

/** Ошибка, текст которой можно безопасно показать пользователю. */
public class BotException extends RuntimeException {
    public BotException(String message) {
        super(message);
    }
}
