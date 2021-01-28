package me.lukasz.rest;

import me.lukasz.presistance.domain.Car;
import me.lukasz.presistance.dto.CarDTO;
import me.lukasz.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public ResponseEntity<CarDTO> create(@RequestBody Car car)
    {
        return new ResponseEntity<>(carService.createCar(car), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CarDTO> update(@PathVariable("id") int id, @RequestBody Car car)
    {
        return new ResponseEntity<>(carService.updateCar(id, car), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CarDTO> delete(@PathVariable("id") int id)
    {
        return carService.deleteCar(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
