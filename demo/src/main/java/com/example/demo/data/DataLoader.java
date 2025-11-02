//package com.example.demo.data;
//
//import com.example.demo.model.Bike;
//import com.example.demo.repository.BikeRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    private final BikeRepository bikeRepository;
//
//    public DataLoader(BikeRepository bikeRepository) {
//        this.bikeRepository = bikeRepository;
//    }
//
//    @Override
//    public void run(String... args) {
//        bikeRepository.save(new Bike(null, "Yamaha", "R15", 160000));
//        bikeRepository.save(new Bike(null, "Kawasaki", "Ninja 300", 350000));
//        bikeRepository.save(new Bike(null, "Royal Enfield", "Classic 350", 220000));
//    }
//}
