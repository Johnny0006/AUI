package com.example.lab3.dto;

import com.example.lab3.entity.Customer;
import lombok.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetCustomerResponse {

    private int id;
    private String name;
    private String surname;

    public static Function<Customer, GetCustomerResponse> entityToDtoMapper(){
        return customer -> GetCustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .build();
    }
}
