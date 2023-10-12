package com.example.bookclub.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UserLoginRequest {
    @Email
    private String email;

    @NotBlank
    private String password;
}
