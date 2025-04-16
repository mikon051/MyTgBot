package com.mystuff.mytgbot;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class MytgbotApplication {
    public static void main(String[] args) {
        try {
            String botToken = "7519567251:AAHGrYPsom0FJsgCYvyI3z53cvaOsWHfERQ";
            TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication();
            botsApplication.registerBot(botToken, new MyAmazingBot());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
