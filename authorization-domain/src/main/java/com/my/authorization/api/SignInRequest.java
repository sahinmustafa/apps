package com.my.authorization.api;

import lombok.Getter;

@Getter
public class SignInRequest {

    private String username;
    private String password;

    public SignInRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public SignInRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
