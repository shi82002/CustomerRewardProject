package com.example.customerrewardsystem.payload.response;

import lombok.Data;

@Data
public class MonthlyRewards {
    private Integer amountSpent;
    private Integer points;
    private String month;

}
