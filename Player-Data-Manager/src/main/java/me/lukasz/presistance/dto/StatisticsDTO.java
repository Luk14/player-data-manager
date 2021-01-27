package me.lukasz.presistance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.lukasz.presistance.domain.User;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatisticsDTO
{

    private int id;
    private int blocks_walked;
    private int blocks_broken;
    private int kills;
    private int deaths;
    private User user;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof StatisticsDTO)) return false;
        StatisticsDTO that = (StatisticsDTO) o;
        return id == that.id && blocks_walked == that.blocks_walked && blocks_broken == that.blocks_broken && kills == that.kills && deaths == that.deaths && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, blocks_walked, blocks_broken, kills, deaths, user);
    }

    @Override
    public String toString()
    {
        return "StatisticsDTO{" +
                "id=" + id +
                ", blocks_walked=" + blocks_walked +
                ", blocks_broken=" + blocks_broken +
                ", kills=" + kills +
                ", deaths=" + deaths +
                ", user=" + user +
                '}';
    }
}
