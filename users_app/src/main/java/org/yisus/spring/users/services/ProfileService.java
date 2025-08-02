package org.yisus.spring.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.yisus.spring.users.anotations.AccessControlAuthorization;
import org.yisus.spring.users.entities.Profile;
import org.yisus.spring.users.entities.User;
import org.yisus.spring.users.repositories.ProfileRepository;
import org.yisus.spring.users.repositories.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@AccessControlAuthorization
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;

    public Profile save(UUID userId,Profile profile) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            profile.setUser(user.get());
            return profileRepository.save(profile);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    public Profile findById(UUID id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Profile with id %s does not exist.", id)));
    }

    public Profile findByUserId(UUID userId) {
        return profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %s does not have a profile",userId)));
    }


    public Page<Profile> findAll(String contain,Integer page,Integer size) {
        if(contain!=null){
            return profileRepository.findByNicknameContains(contain, PageRequest.of(page,size)).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No profiles found containing: %s", contain)));
        }
        return profileRepository.findAll(PageRequest.of(page,size));
    }

    public Profile update(UUID userId,UUID profileId,Profile profile) {
        if (!profileRepository.findByUserIdAndProfileId(userId, profileId).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User %s does not have profile %s",userId,profileId));
        }
        return profileRepository.save(profile);
    }

    public String delete(UUID userId, UUID profileId) {
        if (!profileRepository.findByUserIdAndProfileId(userId, profileId).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User %s does not have profile %s ",userId,profileId));
        }
        profileRepository.deleteById(profileId);
        return String.format("Profile with id %s deleted successfully", profileId);
    }
}
