package com.example.demo.controller;

import com.example.demo.dto.StoreAddressRequestBody;
import com.example.demo.dto.UpdateAddressRequestBody;
import com.example.demo.entity.Address;
import com.example.demo.service.AddressService;

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
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/")
    public Address addAddress(@RequestBody StoreAddressRequestBody addressRequestBody) {
        return this.addressService.addAddress(addressRequestBody);
    }

    @GetMapping("/all")
    public Page<Address> getAllAddress(@RequestParam("page") int page) {
        return this.addressService.getAllAddress(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> findAddressById(@PathVariable int id) {
        return this.addressService.findAddressById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddressById(@PathVariable int id) {
        return this.addressService.deleteAddressById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable int id,
            @RequestBody UpdateAddressRequestBody addressRequestBody) {
        return this.addressService.updateAddress(id, addressRequestBody);

    }

}
