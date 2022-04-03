package com.example.lab3.car.dto;

import com.example.lab3.car.entity.Car;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetCarResponse {

    private String registration;
    private String customer; //owner
    private String mark;
    private int productionYear;

    public static Function<Car, GetCarResponse> entityToDtoMapper(){
        return car -> GetCarResponse.builder()
                .registration(car.getRegistration())
                .mark(car.getMark())
                .productionYear(car.getProductionYear())
                .customer(String.valueOf(car.getCustomer().getId()))
                .build();
    }

}