package com.flowme.telegram.command;

import com.flowme.telegram.model.BotReply;
import com.flowme.telegram.model.IncomingMessage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class AboutCommand extends AbstractCommand {

    private final String text;

    public AboutCommand() {
        super("/about", "описание бота");
        this.text = loadText();
    }

    @Override
    protected BotReply execute(IncomingMessage message) {
        return BotReply.text(text);
    }

    private static String loadText() {
        try (InputStream in = AboutCommand.class.getClassLoader().getResourceAsStream("README.md")) {
            if (in == null) {
                return "Описание не задано";
            }
            return new String(in.readAllBytes(), StandardCharsets.UTF_8).trim();
        } catch (IOException e) {
            return "Описание не задано";
        }
    }
}
