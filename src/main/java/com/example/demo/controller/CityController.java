package com.example.demo.controller;

import com.example.demo.dto.StoreCityRequestBody;
import com.example.demo.dto.UpdateCityRequestBody;
import com.example.demo.entity.City;
import com.example.demo.service.CityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/")
    public City addCity(@RequestBody StoreCityRequestBody cityRequestBody) {
        return this.cityService.addCity(cityRequestBody);
    }

    @GetMapping("/all")
    public Page<City> getAllCities(@RequestParam("page") int page) {
        return this.cityService.getAllCities(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> findCityById(@PathVariable int id) {
        return this.cityService.findCityById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCityById(@PathVariable int id) {
        return this.cityService.deleteCityById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<City> updateCity(@PathVariable int id, @RequestBody UpdateCityRequestBody cityRequestBody) {
        return this.cityService.updateCity(id, cityRequestBody);
    }
}
