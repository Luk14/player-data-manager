package me.lukasz.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.lukasz.PlayerDataManagerApplication;
import me.lukasz.presistance.domain.Car;
import me.lukasz.presistance.dto.CarDTO;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = PlayerDataManagerApplication.class)
@AutoConfigureMockMvc
public class CarControllerTest
{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ObjectMapper objectMapper;

    private Car TEST_CAR;
    private CarDTO TEST_CARDTO;

    private CarDTO mapToCarDTO(Car car)
    {
        return modelMapper.map(car, CarDTO.class);
    }

    @BeforeEach
    public void before()
    {
        this.TEST_CAR = new Car(1, "EX20POL", "2020", "White", 132, 2.3, null);
        this.TEST_CARDTO = mapToCarDTO(TEST_CAR);
    }

    @Test
    public void testRead() throws Exception
    {
        MockHttpServletRequestBuilder mock = MockMvcRequestBuilders.request(HttpMethod.GET, "/car/read/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mock)
                .andExpect(content().json(objectMapper.writeValueAsString(TEST_CARDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testReadAll() throws Exception
    {
        List<CarDTO> carList = new ArrayList<>();
        carList.add(new CarDTO(1, "EX20POL", "2020", "White", 132, 2.3));
        carList.add(new CarDTO(2, "FS15SFE", "2015", "Blue", 100, 15.6));
        carList.add(new CarDTO(3, "EF19DSF", "2019", "Purple", 82, 35.1));

        MockHttpServletRequestBuilder mock = MockMvcRequestBuilders.request(HttpMethod.GET, "/car/readall")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mock)
                .andExpect(content().json(objectMapper.writeValueAsString(carList)))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreate() throws Exception
    {
        MockHttpServletRequestBuilder mock = MockMvcRequestBuilders.request(HttpMethod.POST, "/car/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(TEST_CAR))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mock)
                .andExpect(content().json(objectMapper.writeValueAsString(TEST_CARDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception
    {
        Car car = new Car(1, "FS15SFE", "2015", "Blue", 100, 15.6, null);
        MockHttpServletRequestBuilder mock = MockMvcRequestBuilders.request(HttpMethod.PUT, "/car/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(car))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mock)
                .andExpect(content().json(objectMapper.writeValueAsString(mapToCarDTO(car))))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testDelete() throws Exception
    {
        MockHttpServletRequestBuilder mock = MockMvcRequestBuilders.request(HttpMethod.DELETE, "/car/delete/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mock)
                .andExpect(status().isNoContent());
    }
}
