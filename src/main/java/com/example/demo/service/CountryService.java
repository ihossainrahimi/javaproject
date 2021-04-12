package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Country;
import com.example.demo.repository.CountryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    
    @Autowired
    private CountryRepository countryRepository;

    public void addCountry(Country country){
        this.countryRepository.save(country);
    }

    public List<Country> getAllCountries(){
        return this.countryRepository.findAll();
    }

    public ResponseEntity<Country> findCountryById(int id){
        Optional<Country> country = this.countryRepository.findById(id);
        if(country.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(country.get());
    }

    public ResponseEntity<String> deleteCountryById(int id){
        boolean exist = this.countryRepository.existsById(id);
        if(!exist){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Country by Id "+id+" not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Country by id "+id+" deleted.");
    }

}
