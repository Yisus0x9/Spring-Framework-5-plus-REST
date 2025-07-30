package org.yisus.spring.users.services;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.hibernate.annotations.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.yisus.spring.users.entities.User;
import org.yisus.spring.users.repositories.UserRepository;
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User with id %s does not exist.",id)));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User with email %s does not exist.",email)));
    }


    @Cacheable(value = "users", key = "#contain + '-' + #page + '-' + #size")
    public Page<User> findAll(String contain, Integer page, Integer size) {
        if(contain != null && !contain.isEmpty()) {
            return userRepository.findByNameContains(contain, PageRequest.of(page, size))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Not found users contain %s ",contain)));
        }
        return userRepository.findAll(PageRequest.of(page, size));
    }

    public Page<String> findAllEmails(Integer page, Integer size) {
        return userRepository.findAllEmails(PageRequest.of(page, size));
    }

    public User update(User user,UUID id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User with id %s does not exist.",id));
        }
        return userRepository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    public String delete(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User with id %s does not exist.",id));
        }
        userRepository.deleteById(id);
        return "User with id " + id + " deleted successfully.";
    }
}
