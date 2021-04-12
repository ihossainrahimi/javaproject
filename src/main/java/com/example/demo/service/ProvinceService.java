package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.UpdateProvinceRequestBody;
import com.example.demo.entity.Province;
import com.example.demo.repository.ProvinceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    public void addProvince(Province province) {
        this.provinceRepository.save(province);
    }

    public List<Province> getAllProvinces() {
        return this.provinceRepository.findAll();
    }

    public ResponseEntity<Province> getProvinceById(int id) {
        Optional<Province> province = this.provinceRepository.findById(id);
        if (province.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(province.get());
    }

    public ResponseEntity<String> deleteProvinceById(int id) {
        boolean exist = this.provinceRepository.existsById(id);
        if (!exist) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Province by Id " + id + " does not exist.");
        }
        this.provinceRepository.deleteById(id);
        return ResponseEntity.ok().body("Province by Id " + id + " succesfully deleted.");
    }

    public ResponseEntity<Province> updateProvince(int id, UpdateProvinceRequestBody provinceRequestBody) {
        Optional<Province> province = this.provinceRepository.findById(id);
        if (province.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        province.get().setName(provinceRequestBody.getName());
        return ResponseEntity.ok().body(province.get());
    }
}
