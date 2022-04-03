package com.example.lab2.dto;

import com.example.lab2.entity.Car;
import com.example.lab2.entity.Customer;
import lombok.*;

import java.util.ArrayList;
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
    private List<String> cars;

    public static Function<Customer, GetCustomerResponse> entityToDtoMapper(){
        return customer -> GetCustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .cars(customer.getCars().stream().map( Car::getMark ).collect( Collectors.toList() ))
                .build();
    }
}
