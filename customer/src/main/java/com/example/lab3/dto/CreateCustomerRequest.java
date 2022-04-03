package com.example.lab3.dto;

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
    private String name;
    private String surname;



    public static Function<CreateCustomerRequest, Customer> dtoToEntityMapper(){
        return request -> Customer.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .build();
    }

}
