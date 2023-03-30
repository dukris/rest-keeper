package by.bsuir.restkeeper.web.dto;

import by.bsuir.restkeeper.domain.Address;
import by.bsuir.restkeeper.domain.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record UserDto(

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long id,

        @NotBlank(message = "Name can't be blank!")
        @Length(min = 2, max = 50, message = "Name must include minimum {min} and maximum {max} characters!")
        String name,

        @NotBlank(message = "Surname can't be blank!")
        @Length(min = 2, max = 50, message = "Surname must include minimum {min} and maximum {max} characters!")
        String surname,

        @NotBlank(message = "Email can't be blank!")
        @Email(message = "Not an email!")
        @Length(min = 6, max = 50, message = "Email must include minimum {min} and maximum {max} characters!")
        String email,

        @NotBlank(message = "Password can't be blank!")
        @Length(min = 8, max = 20, message = "Password must include minimum {min} and maximum {max} characters!")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password,

        @NotBlank(message = "Role can't be blank!")
        User.Role role,

        @NotBlank(message = "Number of passport can't be blank!")
        @Length(min = 2, max = 50, message = "Number of passport must include minimum {min} and maximum {max} characters!")
        String passport,

        @NotNull(message = "Date of birth can't be blank!")
        LocalDate dateOfBirth,

        @Positive(message = "Phone number must be positive!")
        Integer phoneNumber,

        @NotBlank(message = "Photo can't be blank!")
        String photoPath,

        @Valid
        Address address

) {
}
