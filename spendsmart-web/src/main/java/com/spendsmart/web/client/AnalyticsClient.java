package com.spendsmart.web.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AnalyticsClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://localhost:8085/api/analytics";

    public Object getMonthlySummary(int userId) {
        return restTemplate.getForObject(BASE_URL + "/monthly/" + userId, Object.class);
    }

    public Object getPlatformAnalytics() {
        return restTemplate.getForObject(BASE_URL + "/platform", Object.class);
    }

    public List<Object> getAllUsers() {
        return restTemplate.getForObject(BASE_URL + "/users", List.class);
    }

    public List<Object> getAllExpenses() {
        return restTemplate.getForObject(BASE_URL + "/expenses", List.class);
    }

    public List<Object> getAllIncomes() {
        return restTemplate.getForObject(BASE_URL + "/incomes", List.class);
    }

    public List<Object> getTopUsers() {
        return restTemplate.getForObject(BASE_URL + "/top-users", List.class);
    }

    public Object generateReport() {
        return restTemplate.getForObject(BASE_URL + "/report", Object.class);
    }

    public List<Object> getAuditLogs() {
        return restTemplate.getForObject(BASE_URL + "/logs", List.class);
    }
}