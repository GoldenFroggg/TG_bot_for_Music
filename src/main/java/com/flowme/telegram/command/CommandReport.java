package com.flowme.telegram.command;

import com.flowme.telegram.model.BotReply;
import com.flowme.telegram.model.IncomingMessage;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Component
public class CommandReport extends AbstractCommand {
    private final TelegramClient telegramClient;

    // ТВОЙ ID (тот, что присылал @userinfobot)
    private static final long ADMIN_ID = 1432180399;

    // ID КЕНТА (попроси у него, пока для теста можешь вставить свой)
    private static final long KENT_ID = 2018409368;

    public CommandReport(TelegramClient telegramClient) {
        super("/report", "Отправить сообщение разработчикам");
        this.telegramClient = telegramClient;
    }

    @Override
    protected BotReply execute(IncomingMessage message) {
        // Получаем текст, который пользователь написал после /report
        String reportText = message.args();

        // Если текст пустой — просим написать
        if (reportText.isEmpty()) {
            return new BotReply("Пожалуйста, напишите текст после команды. Пример: /report У меня всё сломалось");
        }

        // Формируем красивое сообщение для вас с Кентом
        String fullText = "📩 Сообщение от пользователя (ID: " + message.userId() + "):\n\n" + reportText;

        try {
            // Отправляем тебе
            telegramClient.execute(SendMessage.builder().chatId(ADMIN_ID).text(fullText).build());

            // Отправляем Кенту
            telegramClient.execute(SendMessage.builder().chatId(KENT_ID).text(fullText).build());

            // Возвращаем пользователю ответ об успехе
            return new BotReply("✅ Ваше сообщение успешно отправлено разработчикам!");

        } catch (TelegramApiException e) {
            // Если Телеграм выдал ошибку (например, Кент не нажал /start у бота)
            return new BotReply("❌ Ошибка при отправке: " + e.getMessage());
        }
    }
}