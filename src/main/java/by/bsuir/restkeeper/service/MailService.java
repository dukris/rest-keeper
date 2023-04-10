package by.bsuir.restkeeper.service;


import by.bsuir.restkeeper.domain.User;

public interface MailService {

    void send(User user, String template, String subject, String filename, String link);

}
