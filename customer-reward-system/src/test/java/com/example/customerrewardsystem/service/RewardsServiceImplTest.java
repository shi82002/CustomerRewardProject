package com.example.customerrewardsystem.service;


import com.example.customerrewardsystem.payload.request.CustomerRewardsRequest;
import com.example.customerrewardsystem.payload.request.Transaction;
import com.example.customerrewardsystem.payload.response.CustomerRewards;
import com.example.customerrewardsystem.payload.response.CustomerRewardsResponse;
import com.example.customerrewardsystem.util.RewardPointsCalculator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class RewardsServiceImplTest {

    @Mock
    private RewardPointsCalculator rewardPointsCalculator;

    @InjectMocks
    private RewardsServiceImpl rewardsService;


    @Test
    public void testCalculateRewards() {

        Transaction transaction = new Transaction();
        transaction.setCustomerName("John");
        transaction.setMonth("May");
        transaction.setBillAmount(120);

        CustomerRewardsRequest customerRewardsRequest = new CustomerRewardsRequest();
        customerRewardsRequest.setTransactions(Arrays.asList(transaction));

        Mockito.when(rewardPointsCalculator.calculateRewardPoints(anyInt())).thenReturn(3);

        CustomerRewardsResponse customerRewardsResponse = rewardsService.calculateRewards(customerRewardsRequest);
        CustomerRewards customerRewards = customerRewardsResponse.getCustomerRewards().get(0);
        Assertions.assertThat(customerRewardsResponse).isNotNull();
        Assertions.assertThat(customerRewardsResponse.getCustomerRewards()).isNotEmpty();
        Assertions.assertThat(customerRewards.getCustomerName()).isEqualTo("John");
        Assertions.assertThat(customerRewards.getTotalRewardsPoints()).isEqualTo(3);
        Assertions.assertThat(customerRewards.getMonthlyRewards().get(0).getPoints()).isEqualTo(3);


    }


}
