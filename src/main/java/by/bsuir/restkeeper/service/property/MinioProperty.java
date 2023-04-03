package by.bsuir.restkeeper.service.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.minio")
@AllArgsConstructor
@Getter
public class MinioProperty {

    private final String bucket;
    private final String accessKey;
    private final String secretKey;
    private final String url;

}
