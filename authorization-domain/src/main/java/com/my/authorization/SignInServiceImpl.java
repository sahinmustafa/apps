package com.my.authorization;

import com.my.authorization.api.SignInRequest;
import com.my.authorization.api.SignInService;
import com.my.infrastructure.security.TokenProvider;
import com.my.infrastructure.security.user.UserPrincipal;
import com.my.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class SignInServiceImpl implements SignInService {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final UserService userPresenter;

    @Override
    public String signIn(SignInRequest request) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserPrincipal userPrincipal = (UserPrincipal) authenticate.getPrincipal();
        String token = tokenProvider.create(userPrincipal.getId());
        userPresenter.updateToken(userPrincipal.getId(), token);
        return token;
    }
}
