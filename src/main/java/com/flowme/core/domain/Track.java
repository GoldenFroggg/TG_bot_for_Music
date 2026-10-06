package com.flowme.core.domain;

import java.time.Duration;
import java.util.Objects;

/** Неизменяемый объект: инварианты проверяются при создании. */
public record Track(String title, String artist, Duration length) {

    public Track {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Название трека не может быть пустым");
        }
        if (artist == null || artist.isBlank()) {
            throw new IllegalArgumentException("Исполнитель не может быть пустым");
        }
        Objects.requireNonNull(length, "Длительность не может быть null");
    }

    public String format() {
        long seconds = length.getSeconds();
        return "%s — %s (%d:%02d)".formatted(artist, title, seconds / 60, seconds % 60);
    }
}
