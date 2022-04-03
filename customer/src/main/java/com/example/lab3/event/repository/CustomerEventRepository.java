package com.example.lab3.event.repository;

import com.example.lab3.event.dto.CreateCustomerRequest;
import com.example.lab3.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class CustomerEventRepository {

    private RestTemplate restTemplate;

    @Autowired
    public CustomerEventRepository(@Value("${cars.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Customer customer) {
        restTemplate.delete("/customers/{id}", customer.getId());
    }

    public void create(Customer customer) {
        restTemplate.postForLocation("/customers", CreateCustomerRequest.entityToDtoMapper().apply(customer));
    }

}
