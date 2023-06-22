package by.bsuir.restkeeper.web.dto;

import by.bsuir.restkeeper.web.dto.group.OnCreateOrder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;

public record CreateOrderDto(

        @Null(
                groups = {OnCreateOrder.class},
                message = "Id should be blank!"
        )
        Long id,

        @NotNull(
                groups = {OnCreateOrder.class},
                message = "Number of table can't be blank!"
        )
        @Positive(
                groups = {OnCreateOrder.class},
                message = "Number of table must be positive!"
        )
        Integer tableNumber,

        @NotNull(
                groups = {OnCreateOrder.class},
                message = "Amount of guests can't be blank!"
        )
        @Positive(
                groups = {OnCreateOrder.class},
                message = "Amount of guests must be positive!"
        )
        Integer amountOfGuests,

        @Valid
        UserDto user

) {
}
