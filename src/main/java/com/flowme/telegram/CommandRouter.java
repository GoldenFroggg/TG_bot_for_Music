package com.flowme.telegram;

import com.flowme.core.exception.BotException;
import com.flowme.telegram.command.CommandHandler;
import com.flowme.telegram.model.BotReply;
import com.flowme.telegram.model.IncomingMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/** Полиморфизм: роутер знает только интерфейс CommandHandler. */
@Component
public class CommandRouter {

    private static final Logger log = LoggerFactory.getLogger(CommandRouter.class);

    private final Map<String, CommandHandler> handlers;

    public CommandRouter(List<CommandHandler> handlers) {
        this.handlers = handlers.stream()
                .collect(Collectors.toMap(CommandHandler::name, Function.identity()));
    }

    public BotReply route(IncomingMessage message) {
        CommandHandler handler = handlers.get(message.command());
        if (handler == null) {
            return BotReply.text("Неизвестная команда. Список команд: /help");
        }
        try {
            return handler.handle(message);
        } catch (BotException e) {
            return BotReply.text(e.getMessage());
        } catch (Exception e) {
            log.error("Ошибка при выполнении команды {}", message.command(), e);
            return BotReply.text("Что-то пошло не так, попробуйте позже");
        }
    }
}
