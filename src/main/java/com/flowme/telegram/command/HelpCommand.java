package com.flowme.telegram.command;

import com.flowme.telegram.model.BotReply;
import com.flowme.telegram.model.IncomingMessage;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class HelpCommand extends AbstractCommand {

    private final ObjectProvider<CommandHandler> handlers;

    public HelpCommand(ObjectProvider<CommandHandler> handlers) {
        super("/help", "Список команд");
        this.handlers = handlers;
    }

    @Override
    protected BotReply execute(IncomingMessage message) {
        String text = handlers.orderedStream()
                .map(h -> h.name() + " — " + h.description())
                .sorted()
                .collect(Collectors.joining("\n"));
        return BotReply.text("Доступные команды:\n" + text);
    }
}
