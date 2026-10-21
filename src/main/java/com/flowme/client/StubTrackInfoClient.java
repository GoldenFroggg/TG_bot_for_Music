package com.flowme.client;

import com.flowme.core.domain.Track;
import com.flowme.core.port.TrackInfoClient;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * ВРЕМЕННАЯ реализация с данными в памяти, чтобы бот запускался без внешних API.
 * Замените её классом, который ходит в реальный музыкальный сервис,
 * реализующим тот же интерфейс TrackInfoClient (остальной код менять не придётся).
 */
@Component
public class StubTrackInfoClient implements TrackInfoClient {

    private final List<Track> catalog = List.of(
            new Track("Bohemian Rhapsody", "Queen", Duration.ofSeconds(355)),
            new Track("Stairway to Heaven", "Led Zeppelin", Duration.ofSeconds(482)),
            new Track("Smells Like Teen Spirit", "Nirvana", Duration.ofSeconds(301)),
            new Track("Wish You Were Here", "Pink Floyd", Duration.ofSeconds(334)),
            new Track("Karma Police", "Radiohead", Duration.ofSeconds(261))
    );

    @Override
    public Optional<Track> findTrack(String query) {
        String q = query.toLowerCase(Locale.ROOT).trim();
        return catalog.stream()
                .filter(t -> (t.artist() + " " + t.title()).toLowerCase(Locale.ROOT).contains(q))
                .findFirst();
    }

    @Override
    public List<Track> findSimilar(Track base, int limit) {
        return catalog.stream()
                .filter(t -> !t.equals(base))
                .limit(limit)
                .toList();
    }
}
