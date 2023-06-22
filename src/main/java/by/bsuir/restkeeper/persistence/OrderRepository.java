package by.bsuir.restkeeper.persistence;

import by.bsuir.restkeeper.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Find orders by period of time.
     *
     * @param from LocalDateTime
     * @param to LocalDateTime
     * @return List of orders
     */
    List<Order> findByTimeBetween(LocalDateTime from, LocalDateTime to);

    /**
     * Find orders by status.
     *
     * @param status Status
     * @return List of orders
     */
    List<Order> findByStatus(Order.Status status);

    /**
     * Find orders by status and period of time.
     *
     * @param status Status
     * @param from LocalDateTime
     * @param to LocalDateTime
     * @return List of orders
     */
    List<Order> findByStatusAndTimeBetween(
            Order.Status status,
            LocalDateTime from,
            LocalDateTime to
    );

}
