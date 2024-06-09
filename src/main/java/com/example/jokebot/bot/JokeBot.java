package com.example.jokebot.bot;

import com.example.jokebot.model.Joke;
import com.example.jokebot.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Random;

@Component
public class JokeBot extends TelegramLongPollingBot {

    @Autowired
    private JokeService jokeService;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.equals("/joke")) {
                List<Joke> jokes = jokeService.getAllJokes();
                if (!jokes.isEmpty()) {
                    Random random = new Random();
                    Joke joke = jokes.get(random.nextInt(jokes.size()));
                    sendMessage(chatId, joke.getContent());
                } else {
                    sendMessage(chatId, "Нет доступных шуток.");
                }
            }
        }
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "Jokes12908Bot";
    }

    @Override
    public String getBotToken() {
        return "7403933204:AAGtENEAngok2jMdUBFIaDGrLpBP7TJU3qo";
    }
}
