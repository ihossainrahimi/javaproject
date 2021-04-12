package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.UpdateCityRequestBody;
import com.example.demo.entity.City;
import com.example.demo.repository.CityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public void addCity(City city) {
        this.cityRepository.save(city);
    }

    public List<City> getAllCities() {
        return this.cityRepository.findAll();
    }

    public ResponseEntity<City> findCityById(int id) {
        Optional<City> city = this.cityRepository.findById(id);
        if (city.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(city.get());
    }

    public ResponseEntity<String> deleteCityById(int id) {
        boolean exist = this.cityRepository.existsById(id);
        if (!exist) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City by Id " + id + " does not exist.");
        }
        this.cityRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City by Id " + id + " successfully deleted.");
    }

    public ResponseEntity<City> updateCity(int id, UpdateCityRequestBody cityRequestBody){
        Optional<City> city = this.cityRepository.findById(id);
        if(city.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        city.get().setProvinceId(cityRequestBody.getProvinceId());
        city.get().setName(cityRequestBody.getName());
        this.cityRepository.save(city.get());
        return ResponseEntity.ok().body(city.get());
    }

}
