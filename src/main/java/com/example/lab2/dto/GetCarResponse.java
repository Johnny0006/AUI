package com.example.lab2.dto;

import com.example.lab2.entity.Car;
import com.example.lab2.entity.Customer;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
                .customer(car.getCustomer().getName() +" "+ car.getCustomer().getSurname())
                .build();
    }

}
