package by.bsuir.restkeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class RestkeeperApplication {

    /**
     * Application start.
     *
     * @param args Application args
     */
    public static void main(final String[] args) {
        SpringApplication.run(RestkeeperApplication.class, args);
    }

}
