package com.my.infrastructure.security.user;

public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {

    UserPrincipal findById(String id);
}
