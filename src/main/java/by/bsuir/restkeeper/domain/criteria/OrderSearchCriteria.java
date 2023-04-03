package by.bsuir.restkeeper.domain.criteria;

import by.bsuir.restkeeper.domain.Order;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderSearchCriteria {

    private Long tableNumber;
    private LocalDateTime time;
    private Order.Status status;

}