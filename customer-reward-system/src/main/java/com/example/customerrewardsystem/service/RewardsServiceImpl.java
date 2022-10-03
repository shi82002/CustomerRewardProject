package com.example.customerrewardsystem.service;

import com.example.customerrewardsystem.payload.request.CustomerRewardsRequest;
import com.example.customerrewardsystem.payload.request.Transaction;
import com.example.customerrewardsystem.payload.response.CustomerRewards;
import com.example.customerrewardsystem.payload.response.CustomerRewardsResponse;
import com.example.customerrewardsystem.payload.response.MonthlyRewards;
import com.example.customerrewardsystem.util.RewardPointsCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class RewardsServiceImpl implements RewardsService {

    private final RewardPointsCalculator rewardPointsCalculator;


    @Override
    public CustomerRewardsResponse calculateRewards(CustomerRewardsRequest customerRewardsRequest) {
        List<CustomerRewards> customerRewards = calculateCustomerRewards(customerRewardsRequest.getTransactions());
        CustomerRewardsResponse customerRewardsResponse = new CustomerRewardsResponse();
        customerRewardsResponse.setCustomerRewards(customerRewards);
        return customerRewardsResponse;
    }


    private List<CustomerRewards> calculateCustomerRewards(List<Transaction> transactions) {
        List<CustomerRewards> customerRewardsList = new ArrayList<>();
        Map<String, Map<String, MonthlyRewards>> customerMonthlyRewardsPoints = new HashMap<>();
        for (Transaction transaction : transactions) {
            String customerName = transaction.getCustomerName();
            int rewardPoints = rewardPointsCalculator.calculateRewardPoints(transaction.getBillAmount());
            if (customerMonthlyRewardsPoints.containsKey(customerName)) {
                Map<String, MonthlyRewards> monthlyRewardsMap = customerMonthlyRewardsPoints.get(customerName);
                if (monthlyRewardsMap != null) {
                    if (monthlyRewardsMap.containsKey(transaction.getMonth())) {
                        MonthlyRewards monthlyRewards = monthlyRewardsMap.get(transaction.getMonth());
                        monthlyRewards.setAmountSpent(monthlyRewards.getAmountSpent() + transaction.getBillAmount());
                        monthlyRewards.setPoints(monthlyRewards.getPoints() + rewardPoints);
                    } else {
                        MonthlyRewards monthlyRewards = buildMonthlyRewards(transaction, rewardPoints);
                        monthlyRewardsMap.put(transaction.getMonth(), monthlyRewards);
                    }
                } else {
                    MonthlyRewards monthlyRewards = buildMonthlyRewards(transaction, rewardPoints);
                    Map<String, MonthlyRewards> monthlyRewardsMapNew = new HashMap<>();
                    monthlyRewardsMapNew.put(transaction.getMonth(), monthlyRewards);
                }
            } else {
                MonthlyRewards monthlyRewards = buildMonthlyRewards(transaction, rewardPoints);
                Map<String, MonthlyRewards> monthlyRewardsMap = new HashMap<>();
                monthlyRewardsMap.put(transaction.getMonth(), monthlyRewards);
                customerMonthlyRewardsPoints.put(transaction.getCustomerName(), monthlyRewardsMap);
            }

        }

        if (!customerMonthlyRewardsPoints.isEmpty()) {

            for (String customerName : customerMonthlyRewardsPoints.keySet()) {
                Collection<MonthlyRewards> monthlyRewardsList = customerMonthlyRewardsPoints.get(customerName).values();
                CustomerRewards customerRewards = new CustomerRewards();
                customerRewards.setCustomerName(customerName);
                customerRewards.setMonthlyRewards(new ArrayList<>(monthlyRewardsList));
                int totalRewardPoints = customerRewards.getMonthlyRewards().stream().mapToInt(MonthlyRewards::getPoints).sum();
                customerRewards.setTotalRewardsPoints(totalRewardPoints);
                customerRewardsList.add(customerRewards);

            }


        }

        return customerRewardsList;

    }


    private MonthlyRewards buildMonthlyRewards(Transaction transaction, Integer rewardPoints) {
        MonthlyRewards monthlyRewards = new MonthlyRewards();
        monthlyRewards.setMonth(transaction.getMonth());
        monthlyRewards.setAmountSpent(transaction.getBillAmount());
        monthlyRewards.setPoints(rewardPoints);
        return monthlyRewards;
    }

}
