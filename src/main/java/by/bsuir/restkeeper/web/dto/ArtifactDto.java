package by.bsuir.restkeeper.web.dto;

import by.bsuir.restkeeper.web.validator.ValidImage;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record ArtifactDto(

        @ValidImage
        @NotNull(message = "File can't be empty!")
        MultipartFile photo

) {
}
