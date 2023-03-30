package by.bsuir.restkeeper.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public record AddressDto(

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long id,

        @NotBlank(message = "City can't be blank!")
        @Length(min = 2, max = 50, message = "City must include minimum {min} and maximum {max} characters!")
        String city,

        @NotBlank(message = "Street can't be blank!")
        @Length(min = 2, max = 50, message = "Street must include minimum {min} and maximum {max} characters!")
        String street,

        @NotNull(message = "House can't be blank!")
        @Positive(message = "House must be positive!")
        Integer house,

        @Positive(message = "Flat must be positive!")
        Integer flat

) {
}
