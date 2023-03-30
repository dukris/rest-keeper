package by.bsuir.restkeeper.web.dto;

import by.bsuir.restkeeper.domain.Dish;
import by.bsuir.restkeeper.domain.Order;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderDto(

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long id,

        @NotNull(message = "Number of table can't be blank!")
        @Positive(message = "Number of table must be positive!")
        Long tableNumber,

        @Valid
        Dish dish,

        @NotNull(message = "Amount can't be blank!")
        @Positive(message = "Amount must be positive!")
        Integer amount,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        BigDecimal cost,

        @NotNull(message = "Time can't be blank!")
        LocalDateTime time,

        @NotNull(message = "Status can't be blank!")
        Order.Status status

) {
}
