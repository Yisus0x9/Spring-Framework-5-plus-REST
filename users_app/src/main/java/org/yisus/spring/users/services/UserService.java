package org.yisus.spring.users.services;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.yisus.spring.users.entities.User;
import org.yisus.spring.users.repositories.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Faker faker;

    @PostConstruct
    public void init(){
        for (int i = 0; i < 10000; i++) {
            User user = new User();
            user.setName(faker.name().firstName());
            user.setNickname(faker.funnyName().name());
            user.setEmail(faker.internet().emailAddress()+i);
            user.setPassword(String.format("%s", i));
            userRepository.save(user);
        }
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " does not exist."));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User with email " + email + " does not exist."));
    }



    public Page<User> findAll(String contain, Integer page, Integer size) {
        if(contain != null && !contain.isEmpty()) {
            return userRepository.findByNameContains(contain, PageRequest.of(page, size))
                    .orElseThrow(() -> new IllegalArgumentException("No users found containing: " + contain));
        }
        return userRepository.findAll(PageRequest.of(page, size));
    }

    public Page<String> findAllEmails(Integer page, Integer size) {
        return userRepository.findAllEmails(PageRequest.of(page, size));
    }

    public User update(User user,UUID id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with id " + id + " does not exist.");
        }
        return userRepository.save(user);
    }

    public String delete(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with id " + id + " does not exist.");
        }
        userRepository.deleteById(id);
        return "User with id " + id + " deleted successfully.";
    }
}
