package com.example.lab3.car.dto;

import com.example.lab3.car.entity.Car;
import com.example.lab3.customer.entity.Customer;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CreateCarRequest {


    private String registration;
    private Integer customer; //owner
    private String mark;
    private int productionYear;

    public static Function<CreateCarRequest, Car> dtoToEntityMapper(
            Function<Integer, Customer> customerFunction){
            return request -> Car.builder()
                    .registration(request.getRegistration())
                    .mark(request.getMark())
                    .productionYear(request.getProductionYear())
                    .customer(customerFunction.apply(request.getCustomer()))
                    .build();
    }


}
