package com.example.lab2.dto;

import com.example.lab2.entity.Car;
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
