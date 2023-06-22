package by.bsuir.restkeeper.web.dto;

import by.bsuir.restkeeper.domain.User;
import by.bsuir.restkeeper.web.dto.group.OnCreate;
import by.bsuir.restkeeper.web.dto.group.OnCreateOrder;
import by.bsuir.restkeeper.web.dto.group.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record UserDto(

        @Null(
                groups = {OnCreate.class},
                message = "Id should be blank!"
        )
        @NotNull(
                groups = {OnUpdate.class, OnCreateOrder.class},
                message = "Id can't be blank!"
        )
        Long id,

        @Null(
                groups = {OnCreateOrder.class},
                message = "Name should be blank!"
        )
        @NotBlank(
                groups = {OnUpdate.class},
                message = "Name can't be blank!"
        )
        @Length(
                min = 2,
                max = 50,
                groups = {OnUpdate.class},
                message = "Name must include minimum "
                        + "{min} and maximum {max} characters!"
        )
        String name,

        @Null(
                groups = {OnCreateOrder.class},
                message = "Surname should be blank!"
        )
        @Length(
                groups = {OnUpdate.class},
                min = 2,
                max = 50,
                message = "Surname must include minimum "
                        + "{min} and maximum {max} characters!"
        )
        @NotBlank(
                groups = {OnUpdate.class},
                message = "Surname can't be blank!"
        )
        String surname,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        String email,

        @Null(
                groups = {OnCreateOrder.class, OnUpdate.class},
                message = "Role should be blank!"
        )
        @NotBlank(message = "Role can't be blank!")
        User.Role role,

        @Null(
                groups = {OnCreateOrder.class},
                message = "Passport should be blank!"
        )
        @NotBlank(
                groups = {OnUpdate.class},
                message = "Number of passport can't be blank!"
        )
        @Length(
                min = 2,
                max = 50,
                message = "Number of passport must include minimum "
                        + "{min} and maximum {max} characters!")
        String passport,

        @Null(
                groups = {OnCreateOrder.class},
                message = "Passport should be blank!"
        )
        @NotNull(
                groups = {OnUpdate.class},
                message = "Date of birth can't be blank!"
        )
        LocalDate dateOfBirth,

        @Null(
                groups = {OnCreateOrder.class},
                message = "Phone number should be blank!"
        )
        @Positive(
                groups = {OnUpdate.class},
                message = "Phone number must be positive!"
        )
        @NotNull(
                groups = {OnUpdate.class},
                message = "Phone number can't be blank!"
        )
        Long phoneNumber,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        String photoPath,

        @Valid
        AddressDto address,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Boolean enabled,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long score

) {
}
