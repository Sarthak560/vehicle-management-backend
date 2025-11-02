package com.example.demo.service;

import com.example.demo.model.Bike;
import com.example.demo.repository.BikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BikeService {

   private final BikeRepository bikeRepository;


    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public List<Bike> getAllBikes(){
        return bikeRepository.findAll();
    }

    public Bike savebike(Bike bike){
        return bikeRepository.save(bike);
    }
    public Optional<Bike> getBikeById(long id){
        return bikeRepository.findById(id);
    }

    public boolean deleteBike(Long id){
        Optional<Bike> bike = bikeRepository.findById(id);
        if(bike.isPresent())
        {
            bikeRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }
}
