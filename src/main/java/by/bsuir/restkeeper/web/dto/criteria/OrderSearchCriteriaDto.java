package by.bsuir.restkeeper.web.dto.criteria;

import by.bsuir.restkeeper.domain.Order;

import java.time.LocalDate;

public record OrderSearchCriteriaDto(

        LocalDate from,
        LocalDate to,
        Order.Status status

) {
}
