package by.bsuir.restkeeper.persistence;

import by.bsuir.restkeeper.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByTimeBetween(LocalDateTime from, LocalDateTime to);

    List<Order> findByStatus(Order.Status status);

    List<Order> findByStatusAndTimeBetween(Order.Status status, LocalDateTime from, LocalDateTime to);

}
