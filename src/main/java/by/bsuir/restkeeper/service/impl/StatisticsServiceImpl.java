package by.bsuir.restkeeper.service.impl;

import by.bsuir.restkeeper.domain.Dish;
import by.bsuir.restkeeper.domain.Order;
import by.bsuir.restkeeper.domain.Statistics;
import by.bsuir.restkeeper.domain.criteria.OrderSearchCriteria;
import by.bsuir.restkeeper.service.OrderService;
import by.bsuir.restkeeper.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.jooq.lambda.Seq;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final OrderService orderService;

    @Override
    public Statistics getStatistics() {
        Statistics statistics = new Statistics();
        statistics.setDailyRevenue(this.calculateRevenue(
                LocalDate.now().atStartOfDay(), LocalDateTime.now()));
        statistics.setMonthlyRevenue(this.calculateRevenue(
                YearMonth.now().atDay(1).atStartOfDay(), LocalDateTime.now()));
        statistics.setAverageBill(this.getAverageBill());
        statistics.setDailyAmountOfGuests(this.calculateAmountOfGuests(
                LocalDate.now().atStartOfDay(), LocalDateTime.now()));
        statistics.setFirstHalfAmountOfGuests(this.calculateAmountOfGuests(
                LocalDate.now().atStartOfDay(), LocalDate.now().atTime(15, 0)));
        statistics.setLastHalfAmountOfGuests(this.calculateAmountOfGuests(
                LocalDate.now().atTime(15, 0), LocalDate.now().atTime(23, 59)));
        statistics.setDailyDish(this.getPopularDish());
        return statistics;
    }

    private BigDecimal calculateRevenue(LocalDateTime from, LocalDateTime to) {
        List<Order> orders = this.getOrders(from, to);
        return orders.stream()
                .map(Order::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getAverageBill() {
        BigDecimal sum = this.calculateRevenue(LocalDate.now().atStartOfDay(), LocalDateTime.now());
        BigDecimal amount = new BigDecimal(this.getDailyAmountOfOrders());
        if (amount.equals(BigDecimal.ZERO)) {
            return amount;
        }
        return sum.divide(amount, RoundingMode.HALF_UP);
    }

    private Integer getDailyAmountOfOrders() {
        List<Order> orders = this.getOrders(
                LocalDate.now().atStartOfDay(), LocalDateTime.now());
        return orders.size();
    }

    private Integer calculateAmountOfGuests(LocalDateTime from, LocalDateTime to) {
        List<Order> orders = this.getOrders(from, to);
        if(orders.isEmpty()){
            return 0;
        }
        return orders.stream()
                .mapToInt(Order::getAmountOfGuests)
                .sum();
    }

    private List<Order> getOrders(LocalDateTime from, LocalDateTime to) {
        OrderSearchCriteria criteria = new OrderSearchCriteria();
        criteria.setFrom(from.toLocalDate());
        criteria.setTo(to.toLocalDate());
        criteria.setStatus(Order.Status.COMPLETED);
        return this.orderService.retrieveAllByPeriod(from, to, Order.Status.COMPLETED);
    }

    private Dish getPopularDish() {
        List<Order> orders = this.getOrders(
                LocalDate.now().atStartOfDay(), LocalDateTime.now());
        if (orders.isEmpty()) {
            Dish dish = new Dish();
            dish.setName("There is no popular dish today :(");
            return dish;
        }
        List<Dish> dishes = new ArrayList<>();
        for (Order order : orders) {
            Map<Dish, Integer> map = order.getDishAmountMap();
            for (Map.Entry<Dish, Integer> entry : map.entrySet()) {
                for (int i = 0; i < entry.getValue(); i++) {
                    dishes.add(entry.getKey());
                }
            }
        }
        return Seq.of(dishes)
                .mode()
                .orElseThrow()
                .get(0);
    }
}
