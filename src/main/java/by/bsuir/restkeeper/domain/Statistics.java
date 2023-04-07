package by.bsuir.restkeeper.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Statistics {

    private BigDecimal dailyRevenue;
    private BigDecimal monthlyRevenue;
    private BigDecimal averageBill;
    private Integer dailyAmountOfGuests;
    private Integer firstHalfAmountOfGuests;
    private Integer lastHalfAmountOfGuests;
    private Dish dailyDish;

}
