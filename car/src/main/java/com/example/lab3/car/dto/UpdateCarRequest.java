package com.example.lab3.car.dto;

import com.example.lab3.car.entity.Car;
import lombok.*;

import java.util.function.BiFunction;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UpdateCarRequest {
    private String mark;
    private int productionYear;

    public static BiFunction<Car, UpdateCarRequest, Car> dtoToEntityUpdater() {
        return (car, request) -> {
            car.setMark(request.getMark());
            car.setProductionYear(request.getProductionYear());
            return car;
        };
    }

}
