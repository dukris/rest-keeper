package by.bsuir.restkeeper.service.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mail")
@AllArgsConstructor
@Getter
public class MailProperty {

    private final String host;
    private final String username;
    private final String password;
    private final Integer port;
    private final String protocol;

}
