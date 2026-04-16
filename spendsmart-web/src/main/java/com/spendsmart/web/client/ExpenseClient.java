package com.spendsmart.web.client;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExpenseClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://localhost:8081/api/expenses";

    public List<Object> getAllExpenses(int userId) {
        return restTemplate.getForObject(BASE_URL + "/user/" + userId, List.class);
    }

    public void addExpense(Object expense) {
        restTemplate.postForObject(BASE_URL, expense, Object.class);
    }

    public void deleteExpense(int id) {
        restTemplate.delete(BASE_URL + "/" + id);
    }
}