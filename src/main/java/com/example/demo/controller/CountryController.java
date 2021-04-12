package com.example.demo.controller;

import java.util.List;

import com.example.demo.dto.UpdateCountryRequestBody;
import com.example.demo.entity.Country;
import com.example.demo.service.CountryService;

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
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping("/country/")
    public void addCountry(@RequestBody Country country) {
        this.countryService.addCountry(country);
    }

    @GetMapping("/country/all")
    public List<Country> getAllCountries() {
        return this.countryService.getAllCountries();
    }

    @GetMapping("/country/{id}")
    public ResponseEntity<Country> findCountryById(@PathVariable int id) {
        return this.countryService.findCountryById(id);
    }

    @DeleteMapping("/country/{id}")
    public ResponseEntity<String> deleteCountryById(@PathVariable int id) {
        return this.countryService.deleteCountryById(id);
    }

    @PutMapping("/country/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable int id,
            @RequestBody UpdateCountryRequestBody countryRequestBody) {
        return this.countryService.updateCountry(id, countryRequestBody);
    }

}