package com.glitchtako.forum.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glitchtako.forum.model.dto.UserDetailsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class BaseController {

    @Autowired
    private ObjectMapper objectMapper;

    protected Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    protected UserDetailsDTO getPrincipalDTO() throws BadCredentialsException {
        try {
            return (UserDetailsDTO) this.getAuth().getPrincipal();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BadCredentialsException(e.getMessage());
        }
    }

}
