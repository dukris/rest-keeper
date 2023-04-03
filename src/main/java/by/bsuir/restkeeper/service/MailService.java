package by.bsuir.restkeeper.service;


import by.bsuir.restkeeper.domain.User;

public interface MailService {

    void sendMail(User user, String template, String subject, String link);

}
