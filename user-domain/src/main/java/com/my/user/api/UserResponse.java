package com.my.user.api;


import lombok.Getter;

@Getter
class UserResponse {

    private String id;
    private String username;
    private String email;
    private String fullname;

    UserResponse setId(String id) {
        this.id = id;
        return this;
    }

    UserResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    UserResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    UserResponse setFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }
}