package com.flowme.telegram.command;

import com.flowme.telegram.model.BotReply;
import com.flowme.telegram.model.IncomingMessage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AboutCommandTest {

    @Test
    public void testAboutCommandReturnsCorrectText() {
        // 1. Создаем команду, которую будем тестировать
        AboutCommand command = new AboutCommand();

        // 2. Создаем "фейковое" сообщение от пользователя
        IncomingMessage fakeMessage = new IncomingMessage(123L, 456L, "/about", "");

        // 3. Запускаем команду
        BotReply reply = command.handle(fakeMessage);

        // 4. Проверяем, что ответ правильный
        assertEquals("Я музыкальный помощник FlowMe!", reply.text());
    }
}