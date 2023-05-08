package by.bsuir.restkeeper.web.dto;

import by.bsuir.restkeeper.web.dto.group.OnCreate;
import by.bsuir.restkeeper.web.dto.group.OnCreateOrder;
import by.bsuir.restkeeper.web.dto.group.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public record AddressDto(

        @Null(groups = {OnCreate.class, OnCreateOrder.class, OnUpdate.class}, message = "Id should be blank!")
//        @NotNull(groups = {OnUpdate.class}, msg = "Id can't be blank!")
        Long id,

//        @Null(groups = {OnCreateOrder.class}, message = "City should be blank!")
        @NotBlank(message = "City can't be blank!")
        @Length(min = 2, max = 50, message = "City must include minimum {min} and maximum {max} characters!")
        String city,

//        @Null(groups = {OnCreateOrder.class}, message = "Street should be blank!")
        @NotBlank(message = "Street can't be blank!")
        @Length(min = 2, max = 50, message = "Street must include minimum {min} and maximum {max} characters!")
        String street,

//        @Null(groups = {OnCreateOrder.class}, message = "House should be blank!")
        @NotNull(message = "House can't be blank!")
        @Positive(message = "House must be positive!")
        Integer house,

//        @Null(groups = {OnCreateOrder.class}, message = "Flat should be blank!")
        @Positive(message = "Flat must be positive!")
        Integer flat

) {
}
