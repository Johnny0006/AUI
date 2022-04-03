package com.example.lab2.dto;

import com.example.lab2.entity.Car;
import com.example.lab2.entity.Customer;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.function.Function;
import java.util.function.Supplier;

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
