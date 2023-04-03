package by.bsuir.restkeeper.service.config;

import by.bsuir.restkeeper.domain.exception.StorageException;
import by.bsuir.restkeeper.service.property.MailProperty;
import by.bsuir.restkeeper.service.property.MinioProperty;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class ServiceConfig {

    private final MinioProperty minioProperty;
    private final MailProperty mailProperty;

    @Bean
    public MinioClient minioClient() {
        try {
            return MinioClient.builder()
                    .endpoint(minioProperty.getUrl())
                    .credentials(minioProperty.getAccessKey(), minioProperty.getSecretKey())
                    .build();
        } catch (Exception e) {
            throw new StorageException("Unable to connect to minio!");
        }
    }

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailProperty.getHost());
        mailSender.setUsername(mailProperty.getUsername());
        mailSender.setPassword(mailProperty.getPassword());
        mailSender.setPort(mailProperty.getPort());
        Properties properties = mailSender.getJavaMailProperties();
        properties.setProperty("mail.transport.protocol", mailProperty.getProtocol());
        return mailSender;
    }

}
