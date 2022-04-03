package com.example.lab3.event.dto;

import com.example.lab3.entity.Customer;
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

    public static Function<Customer, CreateCustomerRequest> entityToDtoMapper() {
        return entity -> CreateCustomerRequest.builder()
                .id(entity.getId())
                .build();
    }


}
