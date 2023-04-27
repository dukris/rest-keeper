package by.bsuir.restkeeper.web.dto;

import java.math.BigDecimal;

public record StatisticsDto(

        BigDecimal dailyRevenue,
        BigDecimal monthlyRevenue,
        BigDecimal averageBill,
        Integer dailyAmountOfGuests,
        Integer firstHalfAmountOfGuests,
        Integer lastHalfAmountOfGuests,
        DishDto dailyDish

) {
}
