package me.lukasz.services;

import me.lukasz.PlayerDataManagerApplication;
import me.lukasz.presistance.domain.Car;
import me.lukasz.presistance.domain.User;
import me.lukasz.presistance.dto.CarDTO;
import me.lukasz.presistance.dto.UserDTO;
import me.lukasz.presistance.repos.CarRepo;
import me.lukasz.presistance.repos.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = PlayerDataManagerApplication.class)
public class UserServiceTest
{

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    private UserDTO mapToCarDTO(User user)
    {
        return modelMapper.map(user, UserDTO.class);
    }

    private User USER_TEST;
    private UserDTO USERDTO_TEST;

    @BeforeEach
    public void before()
    {
        this.USER_TEST = new User(1, "Luk114", "Lukasz", "Smith", "Luks@gm.co", null);
        this.USERDTO_TEST = mapToCarDTO(USER_TEST);
    }

    @Test
    public void readAllTest()
    {
        List<User> userList = new ArrayList<>();
        List<UserDTO> userDTOList = new ArrayList<>();
        userList.add(USER_TEST);
        userDTOList.add(USERDTO_TEST);
        Mockito.when(userRepo.findAll()).thenReturn(userList);
        assertThat(userDTOList).isEqualTo(userService.readAll());
    }

    @Test
    public void readTest()
    {
        Mockito.when(userRepo.findById(Mockito.anyInt())).thenReturn(java.util.Optional.ofNullable(USER_TEST));
        Mockito.when(userRepo.getOne(Mockito.anyInt())).thenReturn(USER_TEST);
        assertThat(USERDTO_TEST).isEqualTo(userService.getUser(1));
    }

    @Test
    public void readFailTest()
    {
        Mockito.when(userRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        assertNull(userService.getUser(Mockito.anyInt()));
    }

    @Test
    public void createTest()
    {
        Mockito.when(userRepo.save(Mockito.any())).thenReturn(USER_TEST);
        assertThat(USERDTO_TEST).isEqualTo(userService.createUser(USER_TEST));
    }

    @Test
    public void updateCarTest()
    {
        User user =  new User(1, "SamSmith", "Lukasz", "Smith", "Ye@gm.co", null);
        Mockito.when(userRepo.existsById(Mockito.anyInt())).thenReturn(true);
        Mockito.when(userRepo.findById(Mockito.anyInt())).thenReturn(java.util.Optional.ofNullable(USER_TEST));
        Mockito.when(userRepo.save(Mockito.any())).thenReturn(user);
        assertThat(mapToCarDTO(user)).isEqualTo(userService.updateUser(1, user));
    }

    @Test
    public void updateCarFailTest()
    {
        User user =  new User(1, "SamSmith", "Lukasz", "Smith", "Ye@gm.co", null);
        Mockito.when(userRepo.existsById(Mockito.anyInt())).thenReturn(false);
        Mockito.when(userRepo.findById(Mockito.anyInt())).thenReturn(java.util.Optional.ofNullable(USER_TEST));
        Mockito.when(userRepo.save(Mockito.any())).thenReturn(user);
        assertNull(userService.updateUser(1, user));
    }

    @Test
    public void deleteCarTest()
    {
        Mockito.when(userRepo.existsById(Mockito.anyInt())).thenReturn(false);
        assertThat(userService.deleteUser(Mockito.anyInt())).isFalse();
    }

    @Test
    public void deleteCarFailTest()
    {
        Mockito.when(userRepo.existsById(Mockito.anyInt())).thenReturn(true);
        assertThat(userService.deleteUser(Mockito.anyInt())).isTrue();
    }

}
