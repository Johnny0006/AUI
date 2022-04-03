package com.example.lab2.dto;

import com.example.lab2.entity.Car;
import com.example.lab2.entity.Customer;
import lombok.*;


import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UpdateCustomerRequest {
    private String name;
    private String surname;

    public static BiFunction<Customer, UpdateCustomerRequest, Customer> dtoToEntityUpdater() {
        return (customer, request) -> {
            customer.setName(request.getName());
            customer.setSurname(request.getSurname());
            return customer;
        };
    }
}
