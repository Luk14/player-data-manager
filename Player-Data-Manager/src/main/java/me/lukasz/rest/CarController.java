package me.lukasz.rest;

import me.lukasz.presistance.domain.Car;
import me.lukasz.presistance.dto.CarDTO;
import me.lukasz.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("car")
public class CarController
{

    private CarService carService;

    @Autowired
    public CarController(CarService carService)
    {
        this.carService = carService;
    }

    @GetMapping("/readall")
    public ResponseEntity<List<CarDTO>> readAll()
    {
        return ResponseEntity.ok(carService.readAll());
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<CarDTO> read(@PathVariable("id") int id)
    {
        return ResponseEntity.ok(carService.getCar(id));
    }

    @GetMapping("/create")
    public ResponseEntity<CarDTO> create(Car car)
    {
        return new ResponseEntity<>(carService.createCar(car), HttpStatus.CREATED);
    }

}
