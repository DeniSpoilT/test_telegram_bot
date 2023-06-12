package ru.komarov;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.komarov.authorization.AuthorizationService;

public class TestBot extends TelegramLongPollingBot {

    private String username;
    private String token;

    private AuthorizationService authorizationService;
    private ErrorDescriptor descriptor;

    public TestBot() {
        authorizationService = new AuthorizationService();
        descriptor = new ErrorDescriptor();
        BotConfig config = new BotConfig("bot.properties");
        this.username = config.get("bot.username");
        this.token = config.get("bot.token");
    }

    @Override
    public void onUpdateReceived(Update update) {

        var msg = update.getMessage();
        var user = msg.getFrom();
        var id = user.getId();

        System.out.printf("username: %s \n%s wrote %s\n", user.getUserName(), user.getFirstName(), msg.getText());

        if (msg.hasText() && authorizationService.getUserNames().contains(user.getUserName())) {
            String error = msg.getText();
            if (descriptor.getErrors().contains(error)){
                String description = descriptor.getDescription(error);
                String [] answer = description.split(" \\*\\*\\*\\*\\* ");

                for(String s : answer) {
                    System.out.println(s);
                    sendText(id, s);
                }
            } else {
                sendText(id, "Описание ошибки " + error + " не обнаружено.");
            }
        } else {
            sendText(id, "Вы не зарегистрированы.");
        }

    }

    public void sendText(Long who, String what) {
        SendMessage sm = SendMessage.builder().chatId(who.toString()).text(what.toString()).build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return this.username;
    }

    @Override
    public String getBotToken() {
        return this.token;
    }
}
