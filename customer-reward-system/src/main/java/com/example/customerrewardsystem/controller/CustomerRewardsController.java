package com.example.customerrewardsystem.controller;

import com.example.customerrewardsystem.payload.request.CustomerRewardsRequest;
import com.example.customerrewardsystem.payload.response.CustomerRewardsResponse;
import com.example.customerrewardsystem.service.RewardsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rewards")
public class CustomerRewardsController {


    private final RewardsService rewardsService;


    @PostMapping
    public CustomerRewardsResponse getCustomerRewards(@RequestBody CustomerRewardsRequest customerRewardsRequest) {
        return rewardsService.calculateRewards(customerRewardsRequest);

    }


}
