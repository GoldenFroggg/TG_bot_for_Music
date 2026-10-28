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

        // Ожидаемый текст (приводим переводы строк к единому формату \n)
        String expectedText = ("\uD83C\uDFB5 Music Assistant Bot\n" +
                "Telegram-бот — музыкальный помощник. Отправьте боту название трека, и он расскажет о нём всё самое интересное, а затем подберёт похожие композиции.\n" +
                "\n" +
                "https://t.me/flowme_music_bot >Открыть бота в Telegram\n" +
                "О проекте\n" +
                "Music Assistant Bot помогает разобираться в музыке и открывать новую прямо в мессенджере, без установки дополнительных приложений.\n" +
                "Пользователю достаточно написать название песни или исполнителя — бот найдёт трек, покажет информацию о нём и предложит подборку схожих треков.\n" +
                "Возможности\n" +
                "\uD83D\uDD0E Информация о треке: название, исполнитель, альбом, год выхода, жанр\n" +
                "\uD83C\uDFA7 Подбор похожих треков на основе выбранной композиции\n" +
                "\uD83D\uDCAC Простое общение: достаточно отправить сообщение с названием песни\n" +
                "⚡\uFE0F Быстрый ответ за пару секунд\n" +
                "Как пользоваться<\n" +
                "1. Найдите бота в Telegram и нажмите Start.\n" +
                "2. Отправьте название трека, например: Daft Punk — Get Lucky.\n" +
                "3. Получите информацию о треке и список похожих композиций.\n" +
                "Команды\n" +
                "/start — запустить бота\n" +
                "/help — список команд и подсказки\n" +
                "/author — список авторов бота\n" +
                "/about - описание бота\n" +
                "Planchik.md Задачи проекта")
                .replace("\r\n", "\n")
                .trim();

        // Фактический текст от бота (также заменяем \r\n на \n)
        String actualText = reply.text() != null ? reply.text().replace("\r\n", "\n").trim() : "";

        // 4. Проверяем, что ответ правильный
        assertEquals(expectedText, actualText);
    }
}