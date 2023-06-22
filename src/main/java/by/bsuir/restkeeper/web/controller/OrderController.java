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
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * Get all orders.
     *
     * @param criteriaDto Criteria
     * @return List of orders
     */
    @GetMapping
    public List<OrderDto> getAllByCriteria(
            final OrderSearchCriteriaDto criteriaDto
    ) {
        OrderSearchCriteria criteria =
                this.orderSearchCriteriaMapper.toEntity(criteriaDto);
        List<Order> orders = this.orderService.retrieveAllByCriteria(criteria);
        return this.orderMapper.toDto(orders);
    }

    /**
     * Get order by id.
     *
     * @param id Id
     * @return Order
     */
    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable final Long id) {
        Order order = this.orderService.retrieveById(id);
        return this.orderMapper.toDto(order);
    }

    /**
     * Change status of order.
     *
     * @param id Id
     * @param status Status
     * @return List of orders
     */
    @PostMapping("/{id}/status")
    public OrderDto changeStatus(@PathVariable final Long id,
                                 @RequestParam final Order.Status status) {
        Order order = this.orderService.changeStatus(id, status);
        return this.orderMapper.toDto(order);
    }

    /**
     * Create new order.
     *
     * @param orderDto Order
     * @return Order
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("@securityExpressions.hasHallRole() "
            + "|| @securityExpressions.hasAdminRole()")
    public OrderDto create(@Validated(OnCreateOrder.class)
                           @RequestBody final CreateOrderDto orderDto) {
        Order order = this.createOrderMapper.toEntity(orderDto);
        User user = this.userService.retrieveById(order.getUser().getId());
        order.setUser(user);
        order = this.orderService.create(order);
        return this.orderMapper.toDto(order);
    }

    /**
     * Add new dish.
     *
     * @param orderId Order's id
     * @param dishId  Dish's id
     * @param amount Amount of dishes
     * @return Order
     */
    @PostMapping("/{orderId}/dishes/{dishId}")
    public OrderDto addDish(@PathVariable final Long orderId,
                            @PathVariable final Long dishId,
                            @RequestParam final Integer amount) {
        Order order = this.orderService.addDish(orderId, dishId, amount);
        return this.orderMapper.toDto(order);
    }

    /**
     * Submit order.
     *
     * @param id Id
     * @return Order
     */
    @PostMapping("/{id}")
    @PreAuthorize("@securityExpressions.hasHallRole() "
            + "|| @securityExpressions.hasAdminRole()")
    public OrderDto submit(@PathVariable final Long id) {
        Order order = this.orderService.submit(id);
        return this.orderMapper.toDto(order);
    }

    /**
     * Delete order.
     *
     * @param id Id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("@securityExpressions.hasHallRole() "
            + "|| @securityExpressions.hasAdminRole()")
    public void delete(@PathVariable final Long id) {
        this.orderService.delete(id);
    }

}
