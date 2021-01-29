package me.lukasz.services;

import me.lukasz.PlayerDataManagerApplication;
import me.lukasz.presistance.domain.Car;
import me.lukasz.presistance.dto.CarDTO;
import me.lukasz.presistance.repos.CarRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = PlayerDataManagerApplication.class)
public class CarServiceTest
{

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepo carRepo;

    @Autowired
    private ModelMapper modelMapper;

    private CarDTO mapToCarDTO(Car car)
    {
        return modelMapper.map(car, CarDTO.class);
    }

    private Car TEST_CAR;
    private CarDTO TEST_CARDTO;

    @BeforeEach
    public void before()
    {
        this.TEST_CAR = new Car(1, "EX20OPW", "2020", "White", 138, 7.8, null);
        this.TEST_CARDTO = mapToCarDTO(TEST_CAR);
    }

    @Test
    public void readAllTest()
    {
        List<Car> carList = new ArrayList<>();
        List<CarDTO> carDTOList = new ArrayList<>();
        carList.add(TEST_CAR);
        carDTOList.add(TEST_CARDTO);
        Mockito.when(carRepo.findAll()).thenReturn(carList);
        assertThat(carDTOList).isEqualTo(carService.readAll());
    }

    @Test
    public void readTest()
    {
        Mockito.when(carRepo.findById(Mockito.anyInt())).thenReturn(java.util.Optional.ofNullable(TEST_CAR));
        Mockito.when(carRepo.getOne(Mockito.anyInt())).thenReturn(TEST_CAR);
        assertThat(TEST_CARDTO).isEqualTo(carService.getCar(1));
    }

    @Test
    public void readFailTest()
    {
        Mockito.when(carRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        assertNull(carService.getCar(Mockito.anyInt()));
    }

    @Test
    public void createTest()
    {
        Mockito.when(carRepo.save(Mockito.any())).thenReturn(TEST_CAR);
        assertThat(TEST_CARDTO).isEqualTo(carService.createCar(TEST_CAR));
    }

    @Test
    public void updateCarTest()
    {
        Car updatedCar = new Car(1, "EX20WPW", "2019", "Blue", 138, 7.8, null);
        Mockito.when(carRepo.existsById(Mockito.anyInt())).thenReturn(true);
        Mockito.when(carRepo.findById(Mockito.anyInt())).thenReturn(java.util.Optional.ofNullable(TEST_CAR));
        Mockito.when(carRepo.save(Mockito.any())).thenReturn(updatedCar);
        assertThat(mapToCarDTO(updatedCar)).isEqualTo(carService.updateCar(1, updatedCar));
    }

    @Test
    public void updateCarFailTest()
    {
        Car updatedCar = new Car(1, "EX20WPW", "2019", "Blue", 138, 7.8, null);
        Mockito.when(carRepo.existsById(Mockito.anyInt())).thenReturn(false);
        Mockito.when(carRepo.findById(Mockito.anyInt())).thenReturn(java.util.Optional.ofNullable(TEST_CAR));
        Mockito.when(carRepo.save(Mockito.any())).thenReturn(updatedCar);
        assertNull(carService.updateCar(1, updatedCar));
    }

    @Test
    public void deleteCarTest()
    {
        Mockito.when(carRepo.existsById(Mockito.anyInt())).thenReturn(true);
        assertThat(carService.deleteCar(Mockito.anyInt())).isTrue();
    }

    @Test
    public void deleteCarFailTest()
    {
        Mockito.when(carRepo.existsById(Mockito.anyInt())).thenReturn(false);
        assertThat(carService.deleteCar(Mockito.anyInt())).isFalse();
    }


}
