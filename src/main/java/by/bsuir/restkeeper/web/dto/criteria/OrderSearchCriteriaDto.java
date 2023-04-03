package by.bsuir.restkeeper.web.dto.criteria;

import by.bsuir.restkeeper.domain.Order;

import java.time.LocalDateTime;

public record OrderSearchCriteriaDto(

        Long tableNumber,
        LocalDateTime time,
        Order.Status status

) {
}