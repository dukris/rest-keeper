package by.bsuir.restkeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class RestkeeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestkeeperApplication.class, args);
	}

}
