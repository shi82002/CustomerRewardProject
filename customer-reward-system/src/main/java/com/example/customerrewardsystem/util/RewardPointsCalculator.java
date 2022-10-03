package com.example.customerrewardsystem.util;

import org.springframework.stereotype.Component;

@Component
public class RewardPointsCalculator {

    public int calculateRewardPoints(Integer amount) {
        int result = 0;
        if (amount == null || amount <= 0) {
            return result;
        }
        int hundreds = (amount / 100) % 10;

        if (hundreds > 0) {
            int remainingAmount = amount - (hundreds * 100);

            result = 2 * hundreds;
            if (remainingAmount > 0) {
                result = result + 1;
            }
        }
        return result;
    }
}
