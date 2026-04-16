package com.spendsmart.web.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NotificationClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://localhost:8086/api/notifications";

    public List<Object> getNotifications(int userId) {
        return restTemplate.getForObject(BASE_URL + "/user/" + userId, List.class);
    }

    public void sendNotification(String message) {
        restTemplate.postForObject(BASE_URL + "/send?msg=" + message, null, String.class);
    }
}