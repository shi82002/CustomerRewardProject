package com.example.customerrewardsystem.payload.request;

import lombok.Data;

import java.util.List;

@Data
public class CustomerRewardsRequest {

    private List<Transaction> transactions;
}
