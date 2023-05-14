package com.lihao.bookstore.services;

import com.lihao.bookstore.entities.Role;
import com.lihao.bookstore.exceptions.ConstraintException;
import com.lihao.bookstore.repositories.RoleRepository;
import com.lihao.bookstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.lihao.bookstore.entities.ApiUser;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApiUser user = userRepository.findByUserName(username).get();
        if(user == null){
            throw new UsernameNotFoundException("Username {" + username + "} not found!");
        }
        return user;
    }

    public ApiUser save(ApiUser user) {
        if(exists(user.getUserName()))
            throw new ConstraintException(String.format("An existing user account with the same UserName [%s] was found in database.", user.getUserName()));
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        if(user.getRoles() == null) {
            String roleName = "ADMIN";
            Role role = roleRepository.findByName(roleName);
            user.setRoles(new HashSet<>(Arrays.asList(role)));
        }
        return userRepository.save(user);
    }
    public ApiUser update(ApiUser user) {
        return userRepository.save(user);
    }
    public List<ApiUser> findAll() {
        return userRepository.findAll();
    }
    private boolean exists(String userName) {
        return userRepository.findByUserName(userName).isPresent();
    }
}
