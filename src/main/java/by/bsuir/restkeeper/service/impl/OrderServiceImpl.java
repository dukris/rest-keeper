package by.bsuir.restkeeper.service.impl;

import by.bsuir.restkeeper.domain.Order;
import by.bsuir.restkeeper.domain.criteria.OrderSearchCriteria;
import by.bsuir.restkeeper.domain.exception.ResourceNotFoundException;
import by.bsuir.restkeeper.persistence.OrderRepository;
import by.bsuir.restkeeper.service.OrderService;
import lombok.RequiredArgsConstructor;
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
        return orderSearchCriteria.getTableNumber() != null ?
                orderRepository.findByTableNumber(orderSearchCriteria.getTableNumber()) :
                orderSearchCriteria.getTime() != null ?
                        orderRepository.findByTime(orderSearchCriteria.getTime()) :
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
    public Order create(Order order) {
        BigDecimal cost = new BigDecimal(order.getAmount());
        cost = cost.multiply(order.getDish().getPrice());
        order.setCost(cost);
        order.setTime(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        Order foundOrder = retrieveById(order.getId());
        foundOrder.setTableNumber(order.getTableNumber());
        foundOrder.setDish(order.getDish());
        foundOrder.setAmount(order.getAmount());
        foundOrder.setCost(order.getCost());
        foundOrder.setStatus(order.getStatus());
        return orderRepository.save(foundOrder);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }


}
