package com.example.customerrewardsystem.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class CustomerRewards {
    private String customerName;
    private List<MonthlyRewards> monthlyRewards;
    private Integer totalRewardsPoints;

}
