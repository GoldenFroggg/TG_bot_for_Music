package com.flowme.telegram.model;

/** Собственная модель входящего сообщения: внутрь приложения типы Telegram не попадают. */
public record IncomingMessage(long chatId, long userId, String command, String args) {

    public static IncomingMessage from(long chatId, long userId, String text) {
        String[] parts = text.trim().split("\\s+", 2);
        String command = parts[0].split("@")[0].toLowerCase(); // "/track@MyBot" -> "/track"
        String args = parts.length > 1 ? parts[1].trim() : "";
        return new IncomingMessage(chatId, userId, command, args);
    }
}
