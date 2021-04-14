package com.example.demo.service;

import java.util.Optional;

import com.example.demo.dto.StoreProvinceRequestBody;
import com.example.demo.dto.UpdateProvinceRequestBody;
import com.example.demo.entity.Province;
import com.example.demo.repository.ProvinceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    public void addProvince(StoreProvinceRequestBody provinceRequestBody) {
        Province province = new Province();

        province.setName(provinceRequestBody.getName());
        province.setCountryId(provinceRequestBody.getCountryId());
        this.provinceRepository.save(province);
    }

    public Page<Province> getAllProvinces(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.provinceRepository.findAll(pageable);
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
        province.get().setCountryId(provinceRequestBody.getCountryId());
        province.get().setName(provinceRequestBody.getName());
        this.provinceRepository.save(province.get());
        return ResponseEntity.ok().body(province.get());
    }
}
