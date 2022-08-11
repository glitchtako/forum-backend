package com.glitchtako.forum.service;

import com.glitchtako.forum.exception.UserNotFoundException;
import com.glitchtako.forum.model.request.LoginRequest;
import com.glitchtako.forum.model.request.RegisterRequest;
import com.glitchtako.forum.model.response.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request) throws UserNotFoundException;

    void register(RegisterRequest request);
}
