package by.bsuir.restkeeper.service.impl;

import by.bsuir.restkeeper.domain.Dish;
import by.bsuir.restkeeper.domain.Order;
import by.bsuir.restkeeper.domain.criteria.OrderSearchCriteria;
import by.bsuir.restkeeper.domain.exception.ResourceNotFoundException;
import by.bsuir.restkeeper.persistence.OrderRepository;
import by.bsuir.restkeeper.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> retrieveAllByCriteria(OrderSearchCriteria orderSearchCriteria) {
        return orderSearchCriteria.getFrom() != null && orderSearchCriteria.getTo() != null ?
                orderRepository.findByTimeBetween(orderSearchCriteria.getFrom(), orderSearchCriteria.getTo()) :
                orderSearchCriteria.getStatus() != null ?
                        orderRepository.findByStatus(orderSearchCriteria.getStatus()) :
                        orderRepository.findAll();
    }

    @Override
    public Order retrieveById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id = " + id + " not found!"));
    }

    @Override
    public Order changeStatus(Long id, Order.Status status) {
        Order order = retrieveById(id);
        order.setStatus(status);
        return orderRepository.save(order);
    }


    @Override
    public Order create(Order order) {
        order.setStatus(Order.Status.RECEIVED);
        order.setCost(calculateTotalCost(order.getDishes(), order.getAmount()));
        order.setTime(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        Order foundOrder = retrieveById(order.getId());
        foundOrder.setTableNumber(order.getTableNumber());
        foundOrder.setDishes(order.getDishes());
        foundOrder.setAmount(order.getAmount());
        foundOrder.setCost(calculateTotalCost(order.getDishes(), order.getAmount()));
        foundOrder.setStatus(order.getStatus());
        return orderRepository.save(foundOrder);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    private BigDecimal calculateTotalCost(List<Dish> dishes, List<Integer> amount) {
        return StreamUtils.zip(dishes.stream(), amount.stream(),
                (dish, number) -> {
                    BigDecimal sum = new BigDecimal(number);
                    sum = sum.multiply(dish.getPrice());
                    return sum;
                }
        ).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}