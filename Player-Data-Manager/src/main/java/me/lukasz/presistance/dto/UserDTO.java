package me.lukasz.presistance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.lukasz.presistance.domain.Statistics;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO
{

    private int id;
    private String username;
    private String fname;
    private String lname;
    private String email;
    private Statistics statistics;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return id == userDTO.id && Objects.equals(username, userDTO.username) && Objects.equals(fname, userDTO.fname) && Objects.equals(lname, userDTO.lname) && Objects.equals(email, userDTO.email) && Objects.equals(statistics, userDTO.statistics);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, username, fname, lname, email, statistics);
    }

    @Override
    public String toString()
    {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", statistics=" + statistics +
                '}';
    }
}
