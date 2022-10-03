package com.example.customerrewardsystem.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class CustomerRewardsResponse {
   private List<CustomerRewards> customerRewards;
}
