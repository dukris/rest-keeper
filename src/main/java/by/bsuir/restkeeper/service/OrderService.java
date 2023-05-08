package by.bsuir.restkeeper.service;

import by.bsuir.restkeeper.domain.Order;
import by.bsuir.restkeeper.domain.criteria.OrderSearchCriteria;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    List<Order> retrieveAllByCriteria(OrderSearchCriteria orderSearchCriteria);

    List<Order> retrieveAllByPeriod(LocalDateTime from, LocalDateTime to, Order.Status status);

    Order retrieveById(Long id);

    Order changeStatus(Long id, Order.Status status);

    Order create(Order order);

    Order submit(Long id);

    Order addDish(Long orderId, Long dishId, Integer number);

    void delete(Long id);

}
