package com.example.demo.controller;


import com.example.demo.service.BikeService;
import com.example.demo.model.Bike;
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

    public bikeController(BikeService bikeService){
        this.bikeService=bikeService;   }


    @GetMapping
    public ResponseEntity<List<Bike>> getAllBikes(){
        try {
            List<Bike> Bikes= bikeService.getAllBikes();
            return new ResponseEntity<>(Bikes,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Bike>> getBikeById(@PathVariable Long id){
        try {
            Optional<Bike> bike= bikeService.getBikeById(id);
            return new ResponseEntity<>(bike,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Bike> createBike(@RequestBody Bike bike){
        try {
             Bike newCar = bikeService.savebike(bike);
            return new ResponseEntity<>(bike,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBike(@PathVariable Long id){
        try {
            boolean deleted = bikeService.deleteBike(id);
            if(deleted)
            {
                return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Not Deleted",HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting Bike: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
