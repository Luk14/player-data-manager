package me.lukasz.presistance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statistics
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int blocks_walked;
    private int blocks_broken;
    private int kills;
    private int deaths;
    @OneToOne
    private User user;
}
