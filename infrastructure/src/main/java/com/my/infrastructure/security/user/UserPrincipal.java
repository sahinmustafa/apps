package com.my.infrastructure.security.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.validation.ValidationException;
import java.util.Collection;
import java.util.stream.Collectors;

public class UserPrincipal implements org.springframework.security.core.userdetails.UserDetails {

    private final String id;
    private final String username;
    private final String password;
    private final String token;
    private final Collection<? extends GrantedAuthority> authorities;

    UserPrincipal(UserPrincipalBuilder.Builder builder) {
        this.id = builder.getId();
        this.username = builder.getUsername();
        this.password = builder.getPassword();
        this.token = builder.getToken();
        this.authorities = builder
                .getRoles()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void validateToken(String token) {
        if (StringUtils.isEmpty(this.token)) {
            return;
        }

        if (!this.token.equals(token)) {
            throw new ValidationException("Token is not valid");
        }
    }
}
