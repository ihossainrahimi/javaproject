package com.example.demo.controller;

import java.util.List;

import com.example.demo.dto.UpdateCityRequestBody;
import com.example.demo.entity.City;
import com.example.demo.service.CityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/city/")
    public void addCity(@RequestBody City city) {
        this.cityService.addCity(city);
    }

    @GetMapping("/city/all")
    public List<City> getAllCities() {
        return this.cityService.getAllCities();
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<City> findCityById(@PathVariable int id) {
        return this.cityService.findCityById(id);
    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity<String> deleteCityById(@PathVariable int id) {
        return this.cityService.deleteCityById(id);
    }

    @PutMapping("/city/{id}")
    public ResponseEntity<City> updateCity(@PathVariable int id, @RequestBody UpdateCityRequestBody cityRequestBody) {
        return this.cityService.updateCity(id, cityRequestBody);
    }
}
