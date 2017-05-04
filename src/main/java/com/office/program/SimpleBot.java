package com.office.program;

import com.office.model.Question;
import org.hibernate.Session;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.List;

public class SimpleBot extends TelegramLongPollingBot {
    static Session session = HibernateUtil.getSessionFactory().openSession();
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new SimpleBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "YarikBot";
    }

    @Override
    public String getBotToken() {
        return "304112620:AAH4gMIbf684givXzJKl5JKPCJxwIZXlBEU";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        List<Question> questions = findAll();
        int count = 0;
        for (Question q : questions) {
            if (message != null && message.hasText()) {
                if (message.getText().equals(q.getQuestion())) {
                    sendMsg(message, q.getAnswer().getAnswer());
                    count++;

                }
            }
        }
        if (count==0){
            sendMsg(message, "I dont know");
        }
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



    public static List<Question> findAll() {
        session.beginTransaction();
        List<Question> questionList = (List<Question>) session.createQuery("from Question").list();
        session.getTransaction().commit();
        return questionList;
    }
}