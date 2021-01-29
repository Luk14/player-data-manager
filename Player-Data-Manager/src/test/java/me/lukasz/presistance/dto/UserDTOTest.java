package me.lukasz.presistance.dto;

import me.lukasz.PlayerDataManagerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = PlayerDataManagerApplication.class)
public class UserDTOTest
{

    private UserDTO USER = new UserDTO(1, "Lukasz", "Jimo", "Luk@gmail.com", null);

    @Test
    public void getIDTest()
    {
        assertEquals(1, USER.getId());
    }

    @Test
    public void setIDTest()
    {
        USER.setId(2);
        assertEquals(2, USER.getId());
    }

    @Test
    public void getUsernameTest()
    {
        assertEquals("Lukasz", USER.getUsername());
    }

    @Test
    public void setUsernameTest()
    {
        USER.setUsername("Jim");
        assertEquals("Jim", USER.getUsername());
    }

    @Test
    public void getFnameTest()
    {
        assertEquals("Jimo", USER.getFname());
    }

    @Test
    public void setFnameTest()
    {
        USER.setFname("Jimmmy");
        assertEquals("Jimmmy", USER.getFname());
    }

    @Test
    public void getEmailTest()
    {
        assertEquals("Luk@gmail.com", USER.getEmail());
    }

    @Test
    public void setEmailTest()
    {
        USER.setEmail("Sam@gmail.com");
        assertEquals("Sam@gmail.com", USER.getEmail());
    }

    @Test
    public void getCarsTest()
    {
        assertNull(USER.getCars());
    }

    @Test
    public void setCarsTest()
    {
        List<CarDTO> carDTOS = new ArrayList<>();
        carDTOS.add(new CarDTO());
        USER.setCars(carDTOS);
        assertEquals(carDTOS, USER.getCars());
    }




}
