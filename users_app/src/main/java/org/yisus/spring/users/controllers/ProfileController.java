package org.yisus.spring.users.controllers;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yisus.spring.users.DTOs.ProfileDto;
import org.yisus.spring.users.entities.Profile;
import org.yisus.spring.users.services.ProfileService;

import java.util.UUID;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping()
    public ResponseEntity<Page<Profile>> getProfiles(@RequestParam(name = "contain",required = false) String contain, @RequestParam(name="page",required = false,defaultValue = "0") Integer page, @RequestParam(name="size",required = false,defaultValue = "100")Integer size) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(profileService.findAll(contain,page,size), HttpStatus.OK);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<Profile> getProfileById(@PathVariable("profileId") UUID profileId) {
        return new ResponseEntity<>(profileService.findById(profileId), HttpStatus.OK);
    }


    @GetMapping("/byUser/{userId}")
    public ResponseEntity<Profile> getProfileByUserId(@PathVariable("userId") UUID userId) {
        return new ResponseEntity<>(profileService.findByUserId(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody ProfileDto profileDto) {
        return new ResponseEntity<>(profileService.save(profileDto.getUserId(), profileDto.getProfile()), HttpStatus.CREATED);
    }
    
    @PutMapping("/{profileId}")
    public ResponseEntity<Profile> updateProfile(@RequestBody ProfileDto profileDto,@PathVariable("profileId") UUID profileId) {
        return new ResponseEntity<>(profileService.update(profileDto.getUserId(), profileId,profileDto.getProfile()), HttpStatus.OK);
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<String> deleteProfile(@RequestBody ProfileDto profileDto,@PathVariable("profileId") UUID profileId) {
        return new ResponseEntity<>(profileService.delete(profileDto.getUserId(),profileId), HttpStatus.OK);
    }
}
