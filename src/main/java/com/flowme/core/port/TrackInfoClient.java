package com.flowme.core.port;

import com.flowme.core.domain.Track;

import java.util.List;
import java.util.Optional;

/** Порт: бизнес-логика зависит от интерфейса, а не от конкретного внешнего API. */
public interface TrackInfoClient {

    Optional<Track> findTrack(String query);

    List<Track> findSimilar(Track base, int limit);
}
