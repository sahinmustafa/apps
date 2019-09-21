package com.my.user;

import com.my.user.attributes.EMailAddress;
import com.my.user.attributes.Password;
import com.my.user.attributes.Username;
import lombok.Getter;

public class UserBuilder  {

    private UserBuilder() {

    }

    public static UsernameField builder(){
        return new Builder();
    }

    @Getter
    protected static class  Builder implements UsernameField, PasswordField, EMailField, UserCreator {

        private Username username;
        private Password password;
        private EMailAddress email;
        private String fullname;


        @Override
        public PasswordField username(String username) {
            this.username = Username.of(username);
            return this;
        }

        @Override
        public EMailField password(String password) {
            this.password = Password.of(password);
            return this;
        }

        @Override
        public UserCreator email(String email) {
            this.email = EMailAddress.of(email);
            return this;
        }

        @Override
        public UserCreator fullname(String fullname) {
            this.fullname = fullname;
            return this;
        }

        @Override
        public User build() {
            return new User(this);
        }
    }


    public interface UsernameField {
        PasswordField username(String username);
    }

    public interface PasswordField {
        EMailField password(String password);
    }

    public interface EMailField {
        UserCreator email(String email);
    }

    public interface UserCreator {
        UserCreator fullname(String fullname);
        User build();
    }
}

