package com.flowme.core.exception;

public class TrackNotFoundException extends BotException {
    public TrackNotFoundException(String query) {
        super("Трек не найден: " + query);
    }
}
