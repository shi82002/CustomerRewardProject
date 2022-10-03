package com.example.customerrewardsystem.controller;

import com.example.customerrewardsystem.payload.request.CustomerRewardsRequest;
import com.example.customerrewardsystem.payload.request.Transaction;
import com.example.customerrewardsystem.payload.response.CustomerRewards;
import com.example.customerrewardsystem.payload.response.CustomerRewardsResponse;
import com.example.customerrewardsystem.payload.response.MonthlyRewards;
import com.example.customerrewardsystem.service.RewardsService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class CustomerRewardsControllerTest {

    @Mock
    private RewardsService rewardsService;

    @InjectMocks
    private CustomerRewardsController customerRewardsController;

   @Test
    public void testGetCustomerRewards() {
        List<MonthlyRewards> monthlyRewardsList = new ArrayList<>();
        MonthlyRewards monthlyRewards = new MonthlyRewards();
        monthlyRewards.setMonth("May");
        monthlyRewards.setAmountSpent(120);
        monthlyRewards.setPoints(3);
        monthlyRewardsList.add(monthlyRewards);
        CustomerRewards customerRewards = new CustomerRewards();
        customerRewards.setCustomerName("John");
        customerRewards.setMonthlyRewards(monthlyRewardsList);
        CustomerRewardsResponse customerRewardsResponse = new CustomerRewardsResponse();
        customerRewardsResponse.setCustomerRewards(Arrays.asList(customerRewards));
        Mockito.when(rewardsService.calculateRewards(any(CustomerRewardsRequest.class)))
                .thenReturn(customerRewardsResponse);


        Transaction transaction = new Transaction();
        transaction.setCustomerName("John");
        transaction.setMonth("May");
        transaction.setBillAmount(120);

        CustomerRewardsRequest customerRewardsRequest = new CustomerRewardsRequest();
        customerRewardsRequest.setTransactions(Arrays.asList(transaction));

        CustomerRewardsResponse response = customerRewardsController.getCustomerRewards(customerRewardsRequest);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getCustomerRewards()).isNotEmpty();

    }

}
