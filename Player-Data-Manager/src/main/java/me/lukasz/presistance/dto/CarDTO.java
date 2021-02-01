package me.lukasz.presistance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDTO
{

    private int id;
    private String registration;
    private String productionDate;
    private String color;
    private int topSpeed;
    private double zeroToSixty;


}
