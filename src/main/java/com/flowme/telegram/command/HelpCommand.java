package com.flowme.telegram.command;

import com.flowme.telegram.model.BotReply;
import com.flowme.telegram.model.IncomingMessage;
import java.util.stream.Collectors;
import java.util.List;

public class HelpCommand extends AbstractCommand {

    private final List<CommandHandler> handlers;

    public HelpCommand(List<CommandHandler> handlers) {
        super("/help", "Список команд");
        this.handlers = handlers;
    }

    @Override
    protected BotReply execute(IncomingMessage message) {
        String args = message.args().trim().toLowerCase().replace("/", "");
        if (!args.isEmpty()) {
            for (int i = 0; i < handlers.size(); i++) {
                if (handlers.get(i).name().equals("/" + args)) {
                    return BotReply.text(handlers.get(i).name() + " — " + handlers.get(i).description());
                }
            }
            return BotReply.text("Такой команды нет");
        }
        else {
            String text = handlers.stream()
                    .map(h -> h.name() + " — " + h.description())
                    .sorted()
                    .collect(Collectors.joining("\n"));
            return BotReply.text("Доступные команды:\n" + text);
        }
    }
}
