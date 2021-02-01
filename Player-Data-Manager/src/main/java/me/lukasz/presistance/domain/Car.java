package me.lukasz.presistance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String registration;
    private String productionDate;
    private String color;
    private int topSpeed;
    private double zeroToSixty;
    @ManyToOne
    private User user;
}
