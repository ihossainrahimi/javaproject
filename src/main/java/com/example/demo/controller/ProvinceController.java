package com.example.demo.controller;

import com.example.demo.dto.StoreProvinceRequestBody;
import com.example.demo.dto.UpdateProvinceRequestBody;
import com.example.demo.entity.Province;
import com.example.demo.service.ProvinceService;

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
@RequestMapping("/api/provinces")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @PostMapping("/")
    public Province addProvince(@RequestBody StoreProvinceRequestBody provinceRequestBody) {
        return this.provinceService.addProvince(provinceRequestBody);
    }

    @GetMapping("/all")
    public Page<Province> getAllProvinces(@RequestParam("page") int page) {
        return this.provinceService.getAllProvinces(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Province> findProvinceById(@PathVariable int id) {
        return this.provinceService.getProvinceById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProvinceById(@PathVariable int id) {
        return this.provinceService.deleteProvinceById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Province> updateProvince(@PathVariable int id,
            @RequestBody UpdateProvinceRequestBody provinceRequestBody) {
        return this.provinceService.updateProvince(id, provinceRequestBody);
    }
}
