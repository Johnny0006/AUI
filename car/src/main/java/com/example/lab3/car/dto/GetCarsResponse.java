package com.example.lab3.car.dto;


import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetCarsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class Car {

        private String registration;
        private String mark;

    }



    @Singular
    private List<Car> cars;


    public static Function<Collection<com.example.lab3.car.entity.Car>, GetCarsResponse> entityToDtoMapper() {
        return cars -> {
            GetCarsResponseBuilder response = GetCarsResponse.builder();
            cars.stream()
                    .map(car -> Car.builder()
                            .registration(car.getRegistration())
                            .mark(car.getMark())
                            .build())
                    .forEach(response::car);
            return response.build();
        };
    }


}
