package com.example.lab3.dto;

import com.example.lab3.entity.Customer;
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
