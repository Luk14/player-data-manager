package me.lukasz.presistance.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class UserDTO
{

    private int id;
    private String username;
    private String fname;
    private String email;
    private List<CarDTO> cars;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public List<CarDTO> getCars()
    {
        return cars;
    }

    public void setCars(List<CarDTO> cars)
    {
        this.cars = cars;
    }
}
