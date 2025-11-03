package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {
        "http://localhost:3000",
        "https://vehicle-management-frontend-psi.vercel.app"})
@RestController
@RequestMapping("api/user")
public class userController {

    private final UserService userService;

    public userController(UserService userService){
        this.userService=userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> userList(){
        try{
            List<User> userdata=userService.getAllUser();
            return new ResponseEntity<>(userdata,HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> userById(@PathVariable Long id){
        try {
            Optional<User> userdatabyid=userService.getDataById(id);
            return new ResponseEntity<>(userdatabyid,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<User> usercreated(@RequestBody User user){
        try {
            User saveuser=userService.saveuser(user);
            return new ResponseEntity<>(saveuser,HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        List<User> users = userService.findByUsername(user.getUsername());

        for (User u : users) {
            if (u.getPassword().equals(user.getPassword())) {
                return ResponseEntity.ok("Login successful");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> userdeleted(@PathVariable Long id){
        try {
            boolean userdeleted=userService.deletedUser(id);
            return new ResponseEntity<>("User Deleted",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Not Deleted",HttpStatus.CONFLICT);
        }
    }

}
