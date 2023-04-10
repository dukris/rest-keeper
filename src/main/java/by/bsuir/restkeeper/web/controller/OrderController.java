package by.bsuir.restkeeper.web.controller;

import by.bsuir.restkeeper.domain.Order;
import by.bsuir.restkeeper.domain.User;
import by.bsuir.restkeeper.domain.criteria.OrderSearchCriteria;
import by.bsuir.restkeeper.service.OrderService;
import by.bsuir.restkeeper.service.UserService;
import by.bsuir.restkeeper.web.dto.CreateOrderDto;
import by.bsuir.restkeeper.web.dto.OrderDto;
import by.bsuir.restkeeper.web.dto.criteria.OrderSearchCriteriaDto;
import by.bsuir.restkeeper.web.dto.group.OnCreateOrder;
import by.bsuir.restkeeper.web.dto.mapper.CreateOrderMapper;
import by.bsuir.restkeeper.web.dto.mapper.OrderMapper;
import by.bsuir.restkeeper.web.dto.mapper.criteria.OrderSearchCriteriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restkeeper/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final OrderMapper orderMapper;
    private final CreateOrderMapper createOrderMapper;
    private final OrderSearchCriteriaMapper orderSearchCriteriaMapper;

    @GetMapping //statistics: list of orders by period of time, by status
    public List<OrderDto> getAllByCriteria(OrderSearchCriteriaDto criteriaDto) {
        OrderSearchCriteria criteria = orderSearchCriteriaMapper.toEntity(criteriaDto);
        List<Order> orders = orderService.retrieveAllByCriteria(criteria);
        return orderMapper.toDto(orders);
    }

    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable Long id) {
        Order order = orderService.retrieveById(id);
        return orderMapper.toDto(order);
    }

    @PostMapping("/{id}/status") //change status only
    public OrderDto changeStatus(@PathVariable Long id,
                                 @RequestParam Order.Status status) {
        Order order = orderService.changeStatus(id, status);
        return orderMapper.toDto(order);
    }

    @PostMapping//create order with tableNumber, guests and userId: set time, status
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto create(@Validated(OnCreateOrder.class) @RequestBody CreateOrderDto orderDto) {
        Order order = createOrderMapper.toEntity(orderDto);
        User user = userService.retrieveById(order.getUser().getId());
        order.setUser(user);
        order = orderService.create(order);
        return orderMapper.toDto(order);
    }

    @PostMapping("/{orderId}/dishes/{dishId}") //add dishes
    public OrderDto addDish(@PathVariable Long orderId,
                            @PathVariable Long dishId,
                            @RequestParam Integer amount) {
        Order order = orderService.addDish(orderId, dishId, amount);
        return orderMapper.toDto(order);
    }


    @PostMapping("/{id}") //submit order: set cost
    public OrderDto submit(@PathVariable Long id) {
        Order order = orderService.submit(id);
        return orderMapper.toDto(order);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }

}
