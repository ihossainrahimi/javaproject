package com.example.demo.service;

import java.util.Optional;

import com.example.demo.dto.StoreCountryRequestBody;
import com.example.demo.dto.UpdateCountryRequestBody;
import com.example.demo.entity.Country;
import com.example.demo.repository.CountryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    
    @Autowired
    private CountryRepository countryRepository;

    public void addCountry(StoreCountryRequestBody countryRequestBody){
        Country country = new Country();
        country.setName(countryRequestBody.getName());
        this.countryRepository.save(country);
    }

    public Page<Country> getAllCountries(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return this.countryRepository.findAll(pageable);
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
        this.countryRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Country by id "+id+" deleted.");
    }

    public ResponseEntity<Country> updateCountry(int id, UpdateCountryRequestBody countryRequestBody){
        Optional<Country> country = countryRepository.findById(id);
        if(country.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        country.get().setName(countryRequestBody.getName());
        this.countryRepository.save(country.get());
        return ResponseEntity.ok().body(country.get());
    }

}
