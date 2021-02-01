package me.lukasz.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.lukasz.PlayerDataManagerApplication;
import me.lukasz.presistance.domain.Car;
import me.lukasz.presistance.domain.User;
import me.lukasz.presistance.dto.CarDTO;
import me.lukasz.presistance.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PlayerDataManagerApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest
{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ObjectMapper objectMapper;

    private User TEST_USER;
    private UserDTO TEST_USERDTO;

    private UserDTO mapToUserDTO(User user)
    {
        return modelMapper.map(user, UserDTO.class);
    }

    @BeforeEach
    public void before()
    {
        this.TEST_USER = new User(1, "YeetGod3", "Smith", "Jane", "test@gmail.com", null);
        this.TEST_USERDTO = mapToUserDTO(TEST_USER);
    }

    @Test
    @Disabled
    public void testRead() throws Exception
    {
        MockHttpServletRequestBuilder mock = MockMvcRequestBuilders.request(HttpMethod.GET, "/user/read/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mock)
                .andExpect(content().json(objectMapper.writeValueAsString(TEST_USERDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @Disabled
    public void testReadAll() throws Exception
    {
        List<UserDTO> userList = new ArrayList<>();
        userList.add(new UserDTO(1, "YeetGod3", "Smith", "test@gmail.com", Collections.singletonList(new CarDTO(1, "EX20POL", "2020", "White", 132, 2.3))));
        userList.add(new UserDTO(2, "SpanishGod11", "Blazer", "yes@gm.co.gamerz", Collections.singletonList(new CarDTO(2, "FS15SFE", "2015", "Blue", 100, 15.6))));
        userList.add(new UserDTO(3, "JimBoii", "Jimmy", "jimmy@hotm.co", Collections.singletonList(new CarDTO(3, "EF19DSF", "2019", "Purple", 82, 35.1))));

        MockHttpServletRequestBuilder mock = MockMvcRequestBuilders.request(HttpMethod.GET, "/user/readall")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mock)
                .andExpect(content().json(objectMapper.writeValueAsString(userList)))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreate() throws Exception
    {
        MockHttpServletRequestBuilder mock = MockMvcRequestBuilders.request(HttpMethod.POST, "/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(TEST_USER))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mock)
                .andExpect(content().json(objectMapper.writeValueAsString(TEST_USERDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception
    {
        User user = new User(1, "test@gmail.com", "Smith", "Jane", "BetterUsername", new ArrayList<>());
        MockHttpServletRequestBuilder mock = MockMvcRequestBuilders.request(HttpMethod.PUT, "/user/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mock)
                .andExpect(content().json(objectMapper.writeValueAsString(mapToUserDTO(user))))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testDelete() throws Exception
    {
        MockHttpServletRequestBuilder mock = MockMvcRequestBuilders.request(HttpMethod.DELETE, "/user/delete/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mock)
                .andExpect(status().isNoContent());
    }
    
}
