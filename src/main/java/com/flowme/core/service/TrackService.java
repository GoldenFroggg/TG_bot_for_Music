package com.flowme.core.service;

import com.flowme.core.domain.Track;
import com.flowme.core.exception.TrackNotFoundException;
import com.flowme.core.port.TrackInfoClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {

    private static final int SIMILAR_LIMIT = 5;

    private final TrackInfoClient client;

    public TrackService(TrackInfoClient client) {
        this.client = client;
    }

    public Track getTrack(String query) {
        return client.findTrack(query)
                .orElseThrow(() -> new TrackNotFoundException(query));
    }

    public List<Track> getSimilar(String query) {
        Track base = getTrack(query);
        return client.findSimilar(base, SIMILAR_LIMIT);
    }
}
