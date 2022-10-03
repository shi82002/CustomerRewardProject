package com.example.customerrewardsystem.payload.request;

import lombok.Data;

@Data
public class Transaction {
    private String customerName;
    private String month;
    private Integer billAmount;
}
