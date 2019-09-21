package com.my.user;

import com.my.user.attributes.EMailAddress;
import com.my.user.attributes.Username;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(User user);
    Optional<User> fromUsername(Username username);
    Optional<User> fromId(String id);
    Optional<User> fromEmail(EMailAddress email);
    List<User> users();
    void delete(String id);

    void updateToken(String ıd, String token);
}
