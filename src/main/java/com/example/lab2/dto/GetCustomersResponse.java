package com.example.lab2.dto;

import com.example.lab2.entity.Car;
import com.example.lab2.entity.Customer;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetCustomersResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class Customer{
        private int id;
        private String name;
        private String surname;
    }


    @Singular
    private List<Customer> customers;

    public static Function<Collection<com.example.lab2.entity.Customer>, GetCustomersResponse> entityToDtoMapper() {
        return customers -> {
            GetCustomersResponseBuilder response = GetCustomersResponse.builder();
            customers.stream()
                    .map(customer -> Customer.builder()
                            .id(customer.getId())
                            .name(customer.getName())
                            .surname(customer.getSurname())
                            .build())
                    .forEach(response::customer);
            return response.build();
        };
    }
}
