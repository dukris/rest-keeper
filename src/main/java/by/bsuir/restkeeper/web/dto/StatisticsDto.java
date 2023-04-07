package by.bsuir.restkeeper.web.dto;

import by.bsuir.restkeeper.domain.Dish;

import java.math.BigDecimal;

public record StatisticsDto(

        BigDecimal dailyRevenue,
        BigDecimal monthlyRevenue,
        BigDecimal averageBill,
        Integer dailyAmountOfGuests,
        Integer morningAmountOfGuests,
        Integer eveningAmountOfGuests,
        Dish dailyDish

) {
}
