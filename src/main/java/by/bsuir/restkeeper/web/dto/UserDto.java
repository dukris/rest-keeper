package by.bsuir.restkeeper.web.dto;

import by.bsuir.restkeeper.domain.Address;
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

        @Null(groups = {OnCreate.class}, message = "Id should be blank!")
        @NotNull(groups = {OnUpdate.class, OnCreateOrder.class}, message = "Id can't be blank!")
        Long id,

        @NotBlank(groups = {OnUpdate.class}, message = "Name can't be blank!")
        @Length(min = 2, max = 50, groups = {OnUpdate.class}, message = "Name must include minimum {min} and maximum {max} characters!")
        String name,

        @Length(groups = {OnUpdate.class}, min = 2, max = 50, message = "Surname must include minimum {min} and maximum {max} characters!")
        String surname,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        String email,

        @NotBlank(message = "Role can't be blank!")
        User.Role role,

        @NotBlank(groups = {OnUpdate.class}, message = "Number of passport can't be blank!")
        @Length(min = 2, max = 50, message = "Number of passport must include minimum {min} and maximum {max} characters!")
        String passport,

        @NotNull(groups = {OnUpdate.class},message = "Date of birth can't be blank!")
        LocalDate dateOfBirth,

        @Positive(groups = {OnUpdate.class}, message = "Phone number must be positive!")
        Integer phoneNumber,

        @NotBlank(groups = {OnUpdate.class}, message = "Photo can't be blank!")
        String photoPath,

        @Valid
        Address address,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Boolean enabled

) {
}
