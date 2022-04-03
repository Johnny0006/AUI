package com.example.lab1.entity;


import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Car implements Serializable {

    private Customer customer; //owner
    private String registration;
    private String mark;
    private int productionYear;

}
