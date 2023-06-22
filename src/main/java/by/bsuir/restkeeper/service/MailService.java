package by.bsuir.restkeeper.service;


import by.bsuir.restkeeper.domain.User;

public interface MailService {

    /**
     * Send mail.
     *
     * @param user User
     * @param template Template
     * @param subject Subject
     * @param filename Filename
     * @param link Link
     */
    void send(
            User user,
            String template,
            String subject,
            String filename,
            String link
    );

}
