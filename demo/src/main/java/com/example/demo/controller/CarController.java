package com.example.demo.controller;

import com.example.demo.model.Car;
import com.example.demo.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {
        "http://localhost:3000",
        "https://vehicle-management-frontend-psi.vercel.app"})
@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    // GET all cars with pagination
    @GetMapping
    public ResponseEntity<List<Car>> getAllCars(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {


        try {
            List<Car> cars = carService.getAllCars(page, size);
            return new ResponseEntity<>(cars, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // GET car by ID
    @GetMapping("/{id}")
    public Optional<Car> getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        try {
            Car savedCar = carService.saveCar(car);
            return new ResponseEntity<>(savedCar,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    // DELETE car by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        try {
            boolean deleted = carService.deleteCar(id);
            if (deleted) {
                return new ResponseEntity<>("Car deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Car not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting car: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // UPDATE car by ID
    @PutMapping("/updateCar/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        try {
            Optional<Car> carOptional = carService.getCarById(id);
            if (carOptional.isPresent()) {
                Car car = carOptional.get();
                car.setBrand(updatedCar.getBrand());
                car.setModel(updatedCar.getModel());
                car.setPrice(updatedCar.getPrice());

                Car savedCar = carService.saveCar(car);
                return new ResponseEntity<>(savedCar, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
