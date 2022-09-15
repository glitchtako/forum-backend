package com.glitchtako.forum.model.response;

import com.glitchtako.forum.model.dto.UserDetailsDTO;
import com.glitchtako.forum.model.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class LoginResponse {

    private String accessToken;

    private String refreshToken;

    private UserDetailsDTO user;
}
