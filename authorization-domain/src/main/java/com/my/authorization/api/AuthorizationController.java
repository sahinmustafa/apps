package com.my.authorization.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthorizationService authorizationService;
    private final SignInService signInService;

    @PostMapping("/sign-in")
    public ResponseEntity signIn(@RequestBody SignInRequest request){
        String token = signInService.signIn(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody SignUpRequest request){
        authorizationService.signUp(request);
        return ResponseEntity.ok("Successfuly");
    }
}
