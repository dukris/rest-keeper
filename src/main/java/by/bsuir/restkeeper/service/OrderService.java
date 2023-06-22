package by.bsuir.restkeeper.service;

import by.bsuir.restkeeper.domain.Order;
import by.bsuir.restkeeper.domain.criteria.OrderSearchCriteria;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    /**
     * Retrieve all orders.
     *
     * @param orderSearchCriteria Criteria
     * @return List of orders
     */
    List<Order> retrieveAllByCriteria(OrderSearchCriteria orderSearchCriteria);

    /**
     * Retrieve all orders by period of time.
     *
     * @param from LocalDateTime
     * @param to LocalDateTime
     * @param status Status
     * @return List of orders
     */
    List<Order> retrieveAllByPeriod(
            LocalDateTime from,
            LocalDateTime to,
            Order.Status status
    );

    /**
     * Retrieve order by id.
     *
     * @param id Id
     * @return Order
     */
    Order retrieveById(Long id);

    /**
     * Change status of order.
     *
     * @param id Id
     * @param status Status
     * @return Order
     */
    Order changeStatus(Long id, Order.Status status);

    /**
     * Create new order.
     *
     * @param order Order
     * @return Order
     */
    Order create(Order order);

    /**
     * Submit new order.
     *
     * @param id Id
     * @return Order
     */
    Order submit(Long id);

    /**
     * Add dish to order.
     *
     * @param orderId Order's id
     * @param dishId Dish's id
     * @param number Amount of dishes
     * @return Order
     */
    Order addDish(Long orderId, Long dishId, Integer number);

    /**
     * Delete order.
     *
     * @param id Id
     */
    void delete(Long id);

}
