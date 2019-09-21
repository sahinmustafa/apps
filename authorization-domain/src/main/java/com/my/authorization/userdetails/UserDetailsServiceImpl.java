package com.my.authorization.userdetails;

import com.my.infrastructure.security.user.UserDetailsService;
import com.my.infrastructure.security.user.UserPrincipal;
import com.my.infrastructure.security.user.UserPrincipalBuilder;
import com.my.user.User;
import com.my.user.UserService;
import com.my.user.attributes.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserPrincipal findById(String id) {
        Optional<User> user = userService.fromId(id);
        User loggedInUser = user.orElseThrow(() -> new UsernameNotFoundException("User not found from id. [" + id + "]"));
        return getUserPrincipalFromUser(loggedInUser);
    }

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.fromUsername(Username.of(username));
        User loggedInUser = user.orElseThrow(() -> new UsernameNotFoundException("User not found from username. [" + username + "]"));
        return getUserPrincipalFromUser(loggedInUser);
    }


    private UserPrincipal getUserPrincipalFromUser(User user){
        List<String> userRoles = new ArrayList<>();

        return UserPrincipalBuilder
                .builder()
                .username(user.getUsername().getValue())
                .password(user.getPassword().getValue())
                //.addRoles(userRoles.stream().toArray())
                .ok()
                .id(user.getId())
                .token(user.getToken())
                .build();
    }
}
