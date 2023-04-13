package by.bsuir.restkeeper.service.impl;

import by.bsuir.restkeeper.domain.Dish;
import by.bsuir.restkeeper.domain.Order;
import by.bsuir.restkeeper.domain.criteria.OrderSearchCriteria;
import by.bsuir.restkeeper.domain.exception.ResourceNotFoundException;
import by.bsuir.restkeeper.persistence.OrderRepository;
import by.bsuir.restkeeper.service.DishService;
import by.bsuir.restkeeper.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DishService dishService;

    @Override
    public List<Order> retrieveAllByCriteria(OrderSearchCriteria orderSearchCriteria) {
        if (orderSearchCriteria.getFrom() != null &&
                orderSearchCriteria.getTo() != null &&
                orderSearchCriteria.getStatus() != null) {
            return this.orderRepository.findByStatusAndTimeBetween(
                    orderSearchCriteria.getStatus(),
                    orderSearchCriteria.getFrom(),
                    orderSearchCriteria.getTo());
        }
        if (orderSearchCriteria.getFrom() != null && orderSearchCriteria.getTo() != null) {
            return this.orderRepository.findByTimeBetween(orderSearchCriteria.getFrom(), orderSearchCriteria.getTo());
        }
        if (orderSearchCriteria.getStatus() != null) {
            return this.orderRepository.findByStatus(orderSearchCriteria.getStatus());
        } else {
            return this.orderRepository.findAll();
        }
    }

    @Override
    public Order retrieveById(Long id) {
        return this.orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id = " + id + " not found!"));
    }

    @Override
    @Transactional
    public Order changeStatus(Long id, Order.Status status) {
        Order order = this.retrieveById(id);
        order.setStatus(status);
        return this.orderRepository.save(order);
    }


    @Override
    @Transactional
    public Order create(Order order) {
        order.setDishAmountMap(new HashMap<>());
        order.setStatus(Order.Status.RECEIVED);
        order.setTime(LocalDateTime.now());
        order.setCost(BigDecimal.ZERO);
        return this.orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order submit(Long id) {
        Order order = this.retrieveById(id);
        order.setCost(this.calculateTotalCost(order.getDishAmountMap()));
        return this.orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order addDish(Long orderId, Long dishId, Integer number) {
        Order order = this.retrieveById(orderId);
        Dish dish = this.dishService.retrieveById(dishId);
        Map<Dish, Integer> dishAmountMap = order.getDishAmountMap();
        dishAmountMap.put(dish, number);
        order.setDishAmountMap(dishAmountMap);
        return this.orderRepository.save(order);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.orderRepository.deleteById(id);
    }

    private BigDecimal calculateTotalCost(Map<Dish, Integer> dishAmountMap) {
        BigDecimal sum = new BigDecimal(0);
        for (Map.Entry<Dish, Integer> entry : dishAmountMap.entrySet()) {
            BigDecimal element = new BigDecimal(entry.getValue());
            element = element.multiply(entry.getKey().getPrice());
            sum = sum.add(element);
        }
        return sum;
    }

}