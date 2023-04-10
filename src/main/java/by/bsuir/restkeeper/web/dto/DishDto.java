package by.bsuir.restkeeper.web.dto;

import by.bsuir.restkeeper.web.dto.group.OnCreate;
import by.bsuir.restkeeper.web.dto.group.OnCreateOrder;
import by.bsuir.restkeeper.web.dto.group.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public record DishDto (

        @Null(groups = {OnCreate.class, OnCreateOrder.class}, message = "Id should be blank!")
        @NotNull(groups = {OnUpdate.class}, message = "Id can't be blank!")
        Long id,

        @NotBlank(message = "Name of dish can't be blank!")
        @Length(min = 2, max = 50, message = "Name of dish must include minimum {min} and maximum {max} characters!")
        String name,

        @NotNull(message = "Price can't be blank!")
        @Positive(message = "Price must be positive!")
        BigDecimal price,

        @NotNull(message = "Availability can't be blank!")
        Boolean availability

) {
}
