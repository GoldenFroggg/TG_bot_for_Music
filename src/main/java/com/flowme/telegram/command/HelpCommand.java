package com.flowme.telegram.command;

import com.flowme.telegram.model.BotReply;
import com.flowme.telegram.model.IncomingMessage;
import java.util.List;
import java.util.stream.Collectors;

public class HelpCommand extends AbstractCommand {

    private final List<CommandHandler> handlers;

    public HelpCommand(List<CommandHandler> handlers) {
        super("/help", "Список команд");
        this.handlers = handlers;
    }

    @Override
    protected BotReply execute(IncomingMessage message) {
        String text = handlers.stream()
                .map(h -> h.name() + " — " + h.description())
                .sorted()
                .collect(Collectors.joining("\n"));
        return BotReply.text("Доступные команды:\n" + text);
    }
}