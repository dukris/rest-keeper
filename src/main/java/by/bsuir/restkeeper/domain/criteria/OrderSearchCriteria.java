package by.bsuir.restkeeper.domain.criteria;

import by.bsuir.restkeeper.domain.Order;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderSearchCriteria {

    private LocalDate from;
    private LocalDate to;
    private Order.Status status;

}
