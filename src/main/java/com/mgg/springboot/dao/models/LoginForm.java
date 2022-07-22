package com.mgg.springboot.dao.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginForm {
    private String email;
    private String password;
    private String strategy;
    private String accessToken;
    // ...
}