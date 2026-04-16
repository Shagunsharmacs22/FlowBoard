package com.spendsmart.web.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CategoryClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://localhost:8087/api/categories";

    // Get all categories
    public List<Object> getAllCategories(int userId) {
        return restTemplate.getForObject(BASE_URL + "/user/" + userId, List.class);
    }

    // Add category
    public void addCategory(Object category) {
        restTemplate.postForObject(BASE_URL, category, Object.class);
    }

    // Delete category
    public void deleteCategory(int id) {
        restTemplate.delete(BASE_URL + "/" + id);
    }
}