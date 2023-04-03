package by.bsuir.restkeeper.persistence;

import by.bsuir.restkeeper.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByTableNumber(Long tableNumber);

    List<Order> findByTime(LocalDateTime time);

    List<Order> findByStatus(Order.Status status);

}
