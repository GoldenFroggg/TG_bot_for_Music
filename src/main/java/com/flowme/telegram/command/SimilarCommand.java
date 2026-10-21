package com.flowme.telegram.command;

import com.flowme.core.domain.Track;
import com.flowme.core.exception.InvalidArgumentException;
import com.flowme.core.service.TrackService;
import com.flowme.telegram.model.BotReply;
import com.flowme.telegram.model.IncomingMessage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SimilarCommand extends AbstractCommand {

    private final TrackService tracks;

    public SimilarCommand(TrackService tracks) {
        super("/similar", "Похожие треки: /similar <название>");
        this.tracks = tracks;
    }

    @Override
    protected void validate(IncomingMessage message) {
        if (message.args().isBlank()) {
            throw new InvalidArgumentException("Укажите название трека: /similar <название>");
        }
    }

    @Override
    protected BotReply execute(IncomingMessage message) {
        List<Track> similar = tracks.getSimilar(message.args());
        if (similar.isEmpty()) {
            return BotReply.text("Похожих треков не нашлось");
        }
        StringBuilder sb = new StringBuilder("Похожие треки:\n");
        for (int i = 0; i < similar.size(); i++) {
            sb.append(i + 1).append(". ").append(similar.get(i).format()).append('\n');
        }
        return BotReply.text(sb.toString().trim());
    }
}
