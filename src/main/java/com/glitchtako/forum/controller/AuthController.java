package com.glitchtako.forum.controller;

import com.glitchtako.forum.exception.EmailExistedException;
import com.glitchtako.forum.exception.UserNotFoundException;
import com.glitchtako.forum.exception.UsernameExistedException;
import com.glitchtako.forum.model.request.LoginRequest;
import com.glitchtako.forum.model.request.RegisterRequest;
import com.glitchtako.forum.model.request.UpdatePasswordRequest;
import com.glitchtako.forum.model.response.LoginResponse;
import com.glitchtako.forum.model.response.RestResponse;
import com.glitchtako.forum.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

  @Autowired private AuthService authService;

  @PostMapping(value = "/login")
  public RestResponse<LoginResponse> login(@RequestBody LoginRequest request)
      throws UserNotFoundException {
    return RestResponse.ok(this.authService.login(request));
  }

  @PostMapping(value = "/register")
  public RestResponse<Boolean> register(@RequestBody RegisterRequest request)
      throws UsernameExistedException, EmailExistedException {
    this.authService.register(request);
    return RestResponse.ok(true);
  }

  @PreAuthorize("#id == principal.id")
  @PutMapping(value = "/{id}/password")
  public RestResponse<Boolean> updatePassword(
      @PathVariable(value = "id") Long userId, @RequestBody UpdatePasswordRequest request)
      throws UserNotFoundException {
    return RestResponse.ok(this.authService.updatePassword(userId, request));
  }

  //    @PostMapping(value = "/token/refresh")
  //    public RestResponse<String>

}
