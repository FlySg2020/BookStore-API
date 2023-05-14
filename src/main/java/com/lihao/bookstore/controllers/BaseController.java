package com.lihao.bookstore.controllers;

import com.lihao.bookstore.entities.ApiUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController {

    public ApiUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (ApiUser) authentication.getPrincipal();
    }
}
