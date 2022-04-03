package com.example.lab2.dto;

import com.example.lab2.entity.Car;
import com.example.lab2.entity.Customer;
import lombok.*;

import javax.persistence.OneToMany;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateCustomerRequest {

    public static class Car{
        private String registration;
        private String mark;
    }


    private int id;
    private String name;
    private String surname;
//    private List<Car> cars;


    public static Function<CreateCustomerRequest, Customer> dtoToEntityMapper(){
        return request -> Customer.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .build();
    }

}
