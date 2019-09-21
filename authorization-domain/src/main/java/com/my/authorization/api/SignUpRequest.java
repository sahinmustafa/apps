package com.my.authorization.api;

import lombok.Getter;

@Getter
public class SignUpRequest {

    private String username;
    private String password;
    private String email;
    private String fullname;

    public SignUpRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public SignUpRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public SignUpRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public SignUpRequest setFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }
}
