package org.yisus.spring.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.yisus.spring.users.entities.Address;
import org.yisus.spring.users.entities.Profile;
import org.yisus.spring.users.repositories.AddressRepository;
import org.yisus.spring.users.repositories.ProfileRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ProfileRepository profileRepository;

    public Page<Address> findAll(String contain,Integer page,Integer size){
        if(contain!=null && !contain.isEmpty()){
            return addressRepository.findByStreetContains(contain,PageRequest.of(page,size));
        }
        System.out.println("Ejecutando petición");
        return addressRepository.findAll(PageRequest.of(page,size));
    }

    public Address findById(UUID addressId){
        return addressRepository.findById(addressId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Address whit id %s not found",addressId)));
    }

    public Address findByUserId(UUID userId){
        return addressRepository.findByUserId(userId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %s does not have address",userId)));
    }



    public Page<String> findAllCities(Integer page,Integer size){
        return addressRepository.findAllCities(PageRequest.of(page,size));
    }

    public Address save(UUID userId, UUID profileId, Address address){
        Optional<Profile> profile= profileRepository.findByUserIdAndProfileId(userId,profileId);
        if(profile.isPresent()){
            address.setProfile(profile.get());
            return addressRepository.save(address);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %s whit profile %s not found",userId,profileId));
    }

    public Address update(UUID userId,UUID profileId,UUID addressId,Address address){
        if(!addressRepository.findByUserIdAndProfileIdAndAddressId(userId, profileId, addressId).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Nor found Address %s whit profile %s and user %s",addressId,profileId,userId));

        }
        return addressRepository.save(address);
    }

    public String delete(UUID userId,UUID profileId,UUID addressId) {
        if(!addressRepository.findByUserIdAndProfileIdAndAddressId(userId, profileId, addressId).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Nor found Address %s whit profile %s and user %s",addressId,profileId,userId));

        }
        addressRepository.deleteById(addressId);
        return String.format("Address with id %s deleted successfully", addressId);
    }

}
