package com.spendsmart.web.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RecurringClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://localhost:8084/api/recurring";

    // Get all recurring transactions
    public List<Object> getRecurring(int userId) {
        return restTemplate.getForObject(BASE_URL + "/user/" + userId, List.class);
    }

    // Add recurring transaction
    public void addRecurring(Object rec) {
        restTemplate.postForObject(BASE_URL, rec, Object.class);
    }

    // Delete recurring
    public void deleteRecurring(int id) {
        restTemplate.delete(BASE_URL + "/" + id);
    }
}