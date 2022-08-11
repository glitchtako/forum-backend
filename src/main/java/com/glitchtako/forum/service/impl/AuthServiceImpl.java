package com.glitchtako.forum.service.impl;

import com.glitchtako.forum.exception.UserNotFoundException;
import com.glitchtako.forum.model.dto.UserDetailsDTO;
import com.glitchtako.forum.model.entity.Role;
import com.glitchtako.forum.model.entity.User;
import com.glitchtako.forum.model.request.LoginRequest;
import com.glitchtako.forum.model.request.RegisterRequest;
import com.glitchtako.forum.model.response.LoginResponse;
import com.glitchtako.forum.repository.RoleRepository;
import com.glitchtako.forum.repository.UserRepository;
import com.glitchtako.forum.service.AuthService;
import com.glitchtako.forum.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public LoginResponse login(LoginRequest request) throws UserNotFoundException {

        this.userRepository.findByUsername(request.getUsername()).orElseThrow(UserNotFoundException::new);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.jwtUtils.generateJwtToken(authentication);

        UserDetailsDTO authUser = (UserDetailsDTO) authentication.getPrincipal();
//        Set<String> authorities = authUser.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toSet());

        return LoginResponse.builder().jwt(jwt).user(authUser).build();

    }

    @Override
    public void register(RegisterRequest request) {

        User newUser = new User();
        Role role = this.roleRepository.findByName("USER").get();

        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRoles(Set.of(role));
        newUser.setUsername(request.getUsername());

        this.userRepository.save(newUser);
    }
}
