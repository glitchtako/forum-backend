package com.glitchtako.forum.service;

import com.glitchtako.forum.exception.EmailExistedException;
import com.glitchtako.forum.exception.UserNotFoundException;
import com.glitchtako.forum.exception.UsernameExistedException;
import com.glitchtako.forum.model.request.LoginRequest;
import com.glitchtako.forum.model.request.RegisterRequest;
import com.glitchtako.forum.model.request.UpdatePasswordRequest;
import com.glitchtako.forum.model.response.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request) throws UserNotFoundException;

    void register(RegisterRequest request) throws EmailExistedException, UsernameExistedException;

    Boolean updatePassword(Long userId, UpdatePasswordRequest request) throws UserNotFoundException;
}
