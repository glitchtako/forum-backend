package com.glitchtako.forum.controller;

import com.glitchtako.forum.exception.UserNotFoundException;
import com.glitchtako.forum.model.request.LoginRequest;
import com.glitchtako.forum.model.request.RegisterRequest;
import com.glitchtako.forum.model.response.LoginResponse;
import com.glitchtako.forum.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) throws UserNotFoundException {
        return ResponseEntity.ok(this.authService.login(request));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Boolean> register(@RequestBody RegisterRequest request) {
        this.authService.register(request);
        return ResponseEntity.ok(true);
    }

}
