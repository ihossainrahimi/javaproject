package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.UpdateAddressRequestBody;
import com.example.demo.entity.Address;
import com.example.demo.repository.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;


   public List<Address> getAllAddress(){
    return this.addressRepository.findAll();
    }

    public ResponseEntity<Address> findAddressById(int id){

        Optional<Address> address = addressRepository.findById(id);
        if (address.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok().body(address.get());
    }  

    public void addAddress(Address address){
        this.addressRepository.save(address);
    }

    public ResponseEntity<String> deleteAddressById(int id){
        boolean exists = addressRepository.existsById(id);
        if (!exists) {
            return new ResponseEntity<>("Address  by Id " + id + " does not exist.\n" + "check your id and try again.",
                    HttpStatus.BAD_REQUEST);
        }

        this.addressRepository.deleteById(id);
        return new ResponseEntity<>("Address by Id " + id + " succesfully deleted.", HttpStatus.OK);
    }

    public ResponseEntity<Address> updateAddress(int id, UpdateAddressRequestBody addressRequestBody){
        Optional<Address> address = addressRepository.findById(id);
        if(address.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        address.get().setUserId(addressRequestBody.getUserId());
        address.get().setStreet(addressRequestBody.getStreet());
        address.get().setSuite(addressRequestBody.getSuite());
        address.get().setLat(addressRequestBody.getLat());
        address.get().setLog(addressRequestBody.getLog());
        address.get().setCompleteAddress(addressRequestBody.getCompleteAddress());
        this.addressRepository.save(address.get());
        return ResponseEntity.ok().body(address.get());

    }


    
}