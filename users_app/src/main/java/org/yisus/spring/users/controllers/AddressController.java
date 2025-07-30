package org.yisus.spring.users.controllers;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yisus.spring.users.DTOs.AddressDto;
import org.yisus.spring.users.entities.Address;
import org.yisus.spring.users.services.AddressService;

import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping()
    public ResponseEntity<Page<Address>> getAddress(
            @RequestParam(name = "contain",required = false) String contain,
            @RequestParam(name = "page",required = false,defaultValue = "0") Integer page,
            @RequestParam(name="size",required = false,defaultValue = "100") Integer size){
        return new ResponseEntity<>(addressService.findAll(contain,page,size), HttpStatus.OK);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<Address> getAddressById(@PathVariable("addressId")UUID addressId){
        return new ResponseEntity<>(addressService.findById(addressId),HttpStatus.OK);
    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<Address> getAddressByUserId(@PathVariable("userId")UUID userId){
        return new ResponseEntity<>(addressService.findById(userId),HttpStatus.OK);
    }


    @GetMapping("/cities")
    public ResponseEntity<Page<String>> getCities( @RequestParam(name = "page",required = false,defaultValue = "0") Integer page,
                                                   @RequestParam(name="size",required = false,defaultValue = "100") Integer size){
        return new ResponseEntity<>(addressService.findAllCities(page,size),HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody AddressDto addressDto){
        return new ResponseEntity<>(addressService.save(addressDto.getUserId(),addressDto.getProfileId(),addressDto.getAddress()),HttpStatus.CREATED);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<Address> updateAddress(@RequestBody AddressDto addressDto,@PathVariable("addressId")UUID addressId){
        return new ResponseEntity<>(addressService.update(addressDto.getUserId(), addressDto.getProfileId(), addressId, addressDto.getAddress()),HttpStatus.OK);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<String> deleteAddress(@RequestBody AddressDto addressDto,@PathVariable("addressId")UUID addressId){
        return new ResponseEntity<>(addressService.delete(addressDto.getUserId(), addressDto.getProfileId(), addressId),HttpStatus.OK);
    }

}
