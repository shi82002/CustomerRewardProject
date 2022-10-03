package com.example.customerrewardsystem.service;


import com.example.customerrewardsystem.payload.request.CustomerRewardsRequest;
import com.example.customerrewardsystem.payload.response.CustomerRewardsResponse;

public interface RewardsService {
    CustomerRewardsResponse calculateRewards(final CustomerRewardsRequest customerRewardsRequest);
}
