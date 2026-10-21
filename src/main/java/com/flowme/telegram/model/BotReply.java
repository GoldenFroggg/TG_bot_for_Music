package com.flowme.telegram.model;

public record BotReply(String text) {

    public static BotReply text(String text) {
        return new BotReply(text);
    }
}
