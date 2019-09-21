package com.my.infrastructure.security.user;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserPrincipalBuilder {

    private UserPrincipalBuilder(){}
    public static Username builder(){
        return new Builder();
    }

    @Getter
    public static class Builder implements Username, Password, Roles, UserPrincipalCreator{

        private String id;
        private String username;
        private String password;
        private String token;
        private List<String> roles = new ArrayList<>();

        @Override
        public Password username(String username) {
            this.username = username;
            return this;
        }

        @Override
        public Roles password(String password) {
            this.password = password;
            return this;
        }

        @Override
        public Roles addRole(String role) {
            this.roles.add(role);
            return this;
        }

        @Override
        public UserPrincipalCreator ok() {
            return this;
        }

        @Override
        public UserPrincipalCreator addRoles(String... roles) {
            this.roles.addAll(Arrays.asList(roles));
            return this;
        }

        @Override
        public UserPrincipalCreator id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public UserPrincipalCreator token(String token) {
            this.token = token;
            return this;
        }

        @Override
        public UserPrincipal build() {
            return new UserPrincipal(this);
        }
    }

    public interface Username {
        Password username(String username);
    }

    public interface Password {
        Roles password(String password);
    }

    public interface Roles {
        Roles addRole(String role);
        UserPrincipalCreator ok();
        UserPrincipalCreator addRoles(String... roles);
    }

    public interface UserPrincipalCreator {
        UserPrincipalCreator id(String id);
        UserPrincipalCreator token(String token);
        UserPrincipal build();
    }

}
