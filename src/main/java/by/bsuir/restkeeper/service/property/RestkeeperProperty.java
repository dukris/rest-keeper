package by.bsuir.restkeeper.service.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "restkeeper.link")
@AllArgsConstructor
@Getter
public class RestkeeperProperty {

    private final String enable;
    private final String refresh;

}
