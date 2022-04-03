package com.example.lab3.customer.dto;

import com.example.lab3.customer.entity.Customer;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateCustomerRequest {

    private int id;


    public static Function<CreateCustomerRequest, Customer> dtoToEntityMapper() {
        return request -> Customer.builder()
                .id(request.getId())
                .build();
    }

}
