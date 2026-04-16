package com.spendsmart.web.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BudgetClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://localhost:8083/api/budgets";

    public List<Object> getBudgets(int userId) {
        return restTemplate.getForObject(BASE_URL + "/user/" + userId, List.class);
    }

    public void addBudget(Object budget) {
        restTemplate.postForObject(BASE_URL, budget, Object.class);
    }
}