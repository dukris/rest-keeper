package by.bsuir.restkeeper.web.dto;

import by.bsuir.restkeeper.domain.Dish;
import by.bsuir.restkeeper.domain.Order;
import by.bsuir.restkeeper.web.dto.group.OnCreate;
import by.bsuir.restkeeper.web.dto.group.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(

        @Null(groups = {OnCreate.class}, message = "Id should be blank!")
        @NotNull(groups = {OnUpdate.class}, message = "Id can't be blank!")
        Long id,

        @NotNull(message = "Number of table can't be blank!")
        @Positive(message = "Number of table must be positive!")
        Long tableNumber,

        @Valid
        List<Dish> dishes,

        @NotNull(message = "Amount can't be blank!")
        @Positive(message = "Amount must be positive!")
        List<Integer> amount,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        BigDecimal cost,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        LocalDateTime time,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Order.Status status

) {
}
