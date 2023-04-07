package by.bsuir.restkeeper.service;

import by.bsuir.restkeeper.domain.Order;
import by.bsuir.restkeeper.domain.criteria.OrderSearchCriteria;

import java.util.List;

public interface OrderService {

    List<Order> retrieveAllByCriteria(OrderSearchCriteria orderSearchCriteria);

    Order retrieveById(Long id);

    Order changeStatus(Long id, Order.Status status);

    Order create(Order order);

    Order submit(Order order);

    Order addDish(Long orderId, Long dishId, Integer number);

    void delete(Long id);

}
