package com.example.demo.controller;

import java.util.List;

import com.example.demo.dto.UpdateProvinceRequestBody;
import com.example.demo.entity.Province;
import com.example.demo.service.ProvinceService;

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
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @PostMapping("/province/")
    public void addProvince(@RequestBody Province province) {
        this.provinceService.addProvince(province);
    }

    @GetMapping("/province/all")
    public List<Province> getAllProvinces() {
        return this.provinceService.getAllProvinces();
    }

    @GetMapping("/province/{id}")
    public ResponseEntity<Province> findProvinceById(@PathVariable int id) {
        return this.provinceService.getProvinceById(id);
    }

    @DeleteMapping("/province/{id}")
    public ResponseEntity<String> deleteProvinceById(@PathVariable int id) {
        return this.provinceService.deleteProvinceById(id);
    }

    @PutMapping("/province/{id}")
    public ResponseEntity<Province> updateProvince(@PathVariable int id,
            @RequestBody UpdateProvinceRequestBody provinceRequestBody) {
        return this.provinceService.updateProvince(id, provinceRequestBody);
    }
}
