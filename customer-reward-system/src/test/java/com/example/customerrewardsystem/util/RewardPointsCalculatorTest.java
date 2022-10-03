package com.example.customerrewardsystem.util;


import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class RewardPointsCalculatorTest {


    @Test
    @Parameters({
            "120, 3",
            "100,2",
            "30,0",
    })
    public void testCalculateRewardPoints(int amount, int expectedPoints) {
        RewardPointsCalculator rewardPointsCalculator = new RewardPointsCalculator();
        int actualRewardPoints = rewardPointsCalculator.calculateRewardPoints(amount);
        Assertions.assertThat(actualRewardPoints).isEqualTo(expectedPoints);


    }

}
