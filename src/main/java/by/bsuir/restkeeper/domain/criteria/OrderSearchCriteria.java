package by.bsuir.restkeeper.domain.criteria;

import by.bsuir.restkeeper.domain.Order;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderSearchCriteria {

    private LocalDateTime from;
    private LocalDateTime to;
    private Order.Status status;

}