package by.bsuir.restkeeper.web.controller;

import by.bsuir.restkeeper.domain.Order;
import by.bsuir.restkeeper.domain.criteria.OrderSearchCriteria;
import by.bsuir.restkeeper.service.OrderService;
import by.bsuir.restkeeper.web.dto.OrderDto;
import by.bsuir.restkeeper.web.dto.criteria.OrderSearchCriteriaDto;
import by.bsuir.restkeeper.web.dto.group.OnCreate;
import by.bsuir.restkeeper.web.dto.group.OnUpdate;
import by.bsuir.restkeeper.web.dto.mapper.OrderMapper;
import by.bsuir.restkeeper.web.dto.mapper.criteria.OrderSearchCriteriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    private final OrderMapper orderMapper;
    private final OrderSearchCriteriaMapper orderSearchCriteriaMapper;

    @GetMapping
    public List<OrderDto> getAllByCriteria(OrderSearchCriteriaDto criteriaDto) {
        OrderSearchCriteria criteria = orderSearchCriteriaMapper.toEntity(criteriaDto);
        List<Order> orders = orderService.retrieveAllByCriteria(criteria);
        return orderMapper.toDto(orders); //exception with enum: validation (dto), check valid data
    }

    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable Long id) {
        Order order = orderService.retrieveById(id);
        return orderMapper.toDto(order);
    }

    @PostMapping("/{id}")
    public OrderDto changeStatus(@PathVariable Long id,
                                 @RequestParam Order.Status status) {
        Order order = orderService.changeStatus(id, status);
        return orderMapper.toDto(order);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED) //todo create without lists, add endpoint to add dish to list
    public OrderDto create(@Validated(OnCreate.class) @RequestBody OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        order = orderService.create(order);
        return orderMapper.toDto(order);
    }

    @PutMapping("/{id}")
    public OrderDto update(@Validated(OnUpdate.class) @RequestBody OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        order = orderService.update(order);
        return orderMapper.toDto(order);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }

}
