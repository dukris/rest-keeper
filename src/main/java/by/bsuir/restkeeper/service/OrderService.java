package by.bsuir.restkeeper.service;

import by.bsuir.restkeeper.domain.Order;
import by.bsuir.restkeeper.domain.criteria.OrderSearchCriteria;

import java.util.List;

public interface OrderService {

    List<Order> retrieveAllByCriteria(OrderSearchCriteria orderSearchCriteria);

    Order retrieveById(Long id);

    Order create(Order order);

    Order update(Order order);

    void delete(Long id);

}
