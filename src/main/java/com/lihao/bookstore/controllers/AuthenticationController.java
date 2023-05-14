package com.lihao.bookstore.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lihao.bookstore.entities.ApiUser;
import com.lihao.bookstore.entities.JwtResponse;
import com.lihao.bookstore.entities.SigninRequest;
import com.lihao.bookstore.services.UserService;
import com.lihao.bookstore.utils.TokenUtils;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/authentication")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtils tokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @ApiOperation(value = "sign up as a new user")
    @PostMapping(value = "/signup")
    public ResponseEntity<ApiUser> signup(@Valid @RequestBody ApiUser user){
        ApiUser apiUser = userService.save(user);
        return new ResponseEntity<>(apiUser, HttpStatus.CREATED);
    }

    @ApiOperation(value = "login with your username and password")
    @PostMapping(value = "/login")
    public ResponseEntity<JwtResponse> signIn(@RequestBody SigninRequest signInRequest) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userService.loadUserByUsername(signInRequest.getUsername());
        String token = tokenUtil.generateToken(userDetails);
        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }

}
