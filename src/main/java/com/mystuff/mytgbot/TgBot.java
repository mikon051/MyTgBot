package com.mystuff.mytgbot;


import jakarta.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

@Component
public class TgBot extends TelegramLongPollingBot {

    private static final Logger log = Logger.getLogger(TgBot.class);
    @PostConstruct
    public void init(){
        botConnect();
    }
    @Override
    public String getBotToken() {
        return "7519567251:AAHGrYPsom0FJsgCYvyI3z53cvaOsWHfERQ";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            if (message.getText().startsWith("/")) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(update.getMessage().getChatId());
                switch (message.getText()) {
                    case "/start":
                        log.debug("Received Start Command");
                        sendMessage.setText("Hello world");
                    default:
                        log.debug("Received echo");
                        sendMessage.setText("echo: " + update.getMessage().getText());
                }
            }
        }
    }


    @Override
    public String getBotUsername() {
        return "MyStuffFirst_bot";
    }
    public void botConnect() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
            log.info("TelegramAPI started. Look for messages");
        } catch (TelegramApiRequestException e) {
            log.error("Cant Connect. Pause " + 1000 / 1000 + "sec and try again. Error: " + e.getMessage());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
                return;
            }
            botConnect();
        }
    }

}
