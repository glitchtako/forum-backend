package com.glitchtako.forum.model.request;

import lombok.Data;

@Data
public class UpdatePasswordRequest {

    private String oldPassword;

    private String newPassword;

}
