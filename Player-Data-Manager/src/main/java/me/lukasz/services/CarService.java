package me.lukasz.services;

import me.lukasz.presistance.domain.Car;
import me.lukasz.presistance.dto.CarDTO;
import me.lukasz.presistance.repos.CarRepo;
import me.lukasz.utils.MyBeanUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService
{

    private CarRepo carRepo;
    private ModelMapper modelMapper;

    public CarService(CarRepo carRepo, ModelMapper modelMapper)
    {
        this.carRepo = carRepo;
        this.modelMapper = modelMapper;
    }

    private CarDTO mapToDTO(Car car)
    {
        if(car==null)return null;
        return modelMapper.map(car, CarDTO.class);
    }

    public List<CarDTO> readAll()
    {
        return carRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public CarDTO getCar(int id)
    {
        return mapToDTO(carRepo.findById(id).isPresent() ? carRepo.getOne(id) : null);
    }

    public CarDTO createCar(Car car)
    {
        return mapToDTO(carRepo.save(car));
    }

    public CarDTO updateCar(int id, Car car)
    {
        if (!carRepo.existsById(id)) return null;
        Car update = carRepo.findById(id).get();
        MyBeanUtil.mergeNotNull(car, update);
        return mapToDTO(carRepo.save(update));
    }

    public boolean deleteCar(int id)
    {
        carRepo.deleteById(id);
        return carRepo.existsById(id);
    }

}
