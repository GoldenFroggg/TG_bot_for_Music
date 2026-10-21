package com.flowme.telegram.command;

import com.flowme.core.exception.InvalidArgumentException;
import com.flowme.core.service.TrackService;
import com.flowme.telegram.model.BotReply;
import com.flowme.telegram.model.IncomingMessage;
import org.springframework.stereotype.Component;

@Component
public class TrackCommand extends AbstractCommand {

    private final TrackService tracks;

    public TrackCommand(TrackService tracks) {
        super("/track", "Информация о треке: /track <название>");
        this.tracks = tracks;
    }

    @Override
    protected void validate(IncomingMessage message) {
        if (message.args().isBlank()) {
            throw new InvalidArgumentException("Укажите название трека: /track <название>");
        }
    }

    @Override
    protected BotReply execute(IncomingMessage message) {
        return BotReply.text(tracks.getTrack(message.args()).format());
    }
}
