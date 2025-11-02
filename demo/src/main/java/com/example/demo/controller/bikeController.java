package com.example.demo.controller;

import com.example.demo.model.Bike;
import com.example.demo.service.BikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/bike")
public class bikeController {

    private final BikeService bikeService;

    public bikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    // ✅ Get all bikes
    @GetMapping
    public ResponseEntity<List<Bike>> getAllBikes() {
        try {
            List<Bike> bikes = bikeService.getAllBikes();
            return new ResponseEntity<>(bikes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ✅ Get bike by ID
    @GetMapping("/{id}")
    public ResponseEntity<Bike> getBikeById(@PathVariable Long id) {
        Optional<Bike> bike = bikeService.getBikeById(id);
        return bike.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // ✅ Create new bike
    @PostMapping
    public ResponseEntity<Bike> createBike(@RequestBody Bike bike) {
        try {
            Bike newBike = bikeService.savebike(bike);
            return new ResponseEntity<>(newBike, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // ✅ Delete bike
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBike(@PathVariable Long id) {
        try {
            boolean deleted = bikeService.deleteBike(id);
            if (deleted) {
                return new ResponseEntity<>("Bike deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Bike not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting bike: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ✅ Update existing bike
    @PutMapping("/{id}")
    public ResponseEntity<Bike> updateBike(@PathVariable Long id, @RequestBody Bike updatedBike) {
        try {
            Optional<Bike> existingBike = bikeService.getBikeById(id);

            if (existingBike.isPresent()) {
                Bike bike = existingBike.get();
                bike.setBrand(updatedBike.getBrand());
                bike.setModel(updatedBike.getModel());
                bike.setPrice(updatedBike.getPrice());

                Bike savedBike = bikeService.savebike(bike);
                return new ResponseEntity<>(savedBike, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
