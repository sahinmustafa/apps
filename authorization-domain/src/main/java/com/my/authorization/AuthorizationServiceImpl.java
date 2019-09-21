package com.my.authorization;

import com.my.authorization.api.AuthorizationService;
import com.my.authorization.api.SignUpRequest;
import com.my.authorization.role.Role;
import com.my.authorization.role.RoleService;
import com.my.infrastructure.exception.BadRequestException;
import com.my.user.User;
import com.my.user.UserBuilder;
import com.my.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AuthorizationServiceImpl implements AuthorizationService {

    private final UserService userService;
    private final RoleService roleService;


    @Override
    public void signUp(SignUpRequest request) {
        User user = getUser(request);
        userService.save(user);
        Role defaulRole = roleService.getDefaulRole();
        roleService.addUser(defaulRole.getId(), user.getId());
    }

    private User getUser(SignUpRequest request) {
        if (request == null) throw new BadRequestException("Request cannot be null");
        return UserBuilder.builder().
                username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .fullname(request.getFullname())
                .build();
    }
}
