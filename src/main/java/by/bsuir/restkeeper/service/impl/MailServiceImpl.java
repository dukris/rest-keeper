package by.bsuir.restkeeper.service.impl;


import by.bsuir.restkeeper.domain.User;
import by.bsuir.restkeeper.service.MailService;
import by.bsuir.restkeeper.service.property.MailProperty;
import freemarker.template.Configuration;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final Configuration configuration;
    private final JavaMailSender javaMailSender;
    private final MailProperty mailProperty;

    @Override
    @SneakyThrows
    public void send(User user, String template, String subject, String filename, String link) {
        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject(subject);
        helper.setFrom(this.mailProperty.getUsername());
        helper.setTo(user.getEmail());
        String emailContent = this.getContent(user, template, link);
        helper.setText(emailContent, true);
        if (!" ".equals(filename)) {
            helper.addAttachment(filename, new File(filename));
        }
        this.javaMailSender.send(mimeMessage);
    }

    @SneakyThrows
    private String getContent(User user, String template, String link) {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        if (!" ".equals(link)) {
            model.put("link", link);
        }
        this.configuration.getTemplate(template).process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }

}
