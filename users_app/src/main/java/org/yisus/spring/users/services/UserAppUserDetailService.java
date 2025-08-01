package org.yisus.spring.users.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.yisus.spring.users.entities.Role;
import org.yisus.spring.users.entities.User;
import org.yisus.spring.users.repositories.UserInRoleRepository;
import org.yisus.spring.users.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserAppUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInRoleRepository userInRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final Logger log= LoggerFactory.getLogger(UserAppUserDetailService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByName(username);
        if(userOptional.isPresent()){
            User user=userOptional.get();
            List<Role> listRoles = userInRoleRepository.findRolesByUserId(user.getId(), PageRequest.of(0, 100)).get().toList();
            String []roles = listRoles.stream().map(Role::getName).toArray(String[]::new);
            return org.springframework.security.core.userdetails.User.withUsername(user.getName()).
                    password(passwordEncoder.encode(user.getPassword())).
                    roles(roles).build();
        }
        throw new UsernameNotFoundException(String.format("User %s not found",username));
    }
}
