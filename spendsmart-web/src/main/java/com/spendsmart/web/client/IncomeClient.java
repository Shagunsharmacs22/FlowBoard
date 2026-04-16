package com.spendsmart.web.client;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IncomeClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://localhost:8082/api/income";

    public List<Object> getAllIncomes(int userId) {
        return restTemplate.getForObject(BASE_URL + "/user/" + userId, List.class);
    }

    public void addIncome(Object income) {
        restTemplate.postForObject(BASE_URL, income, Object.class);
    }
}