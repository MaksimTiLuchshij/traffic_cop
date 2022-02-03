package com.example.demo.Controller;

import com.example.demo.Service.CarNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CarNumberController{
    @Autowired
    private CarNumberService carNumberService;

    @GetMapping("/random")
    public String getRandomNumber(){
        return carNumberService.random();
    }
    @GetMapping("/next")
    public String getNextNumber(){
        return carNumberService.next();
    }
}
