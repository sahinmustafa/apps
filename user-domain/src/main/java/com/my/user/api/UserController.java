package com.my.user.api;

import com.my.app.aws.adapter.sns.SnsPublisher;
import com.my.user.User;
import com.my.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final SnsPublisher publisher;

    @GetMapping("")
    public ResponseEntity<List<UserResponse>> users(){
        List<UserResponse> response = userService
                .users()
                .stream()
                .map(u -> new UserResponse()
                        .setUsername(u.getUsername().getValue())
                        .setEmail(u.getEmail().getValue())
                        .setFullname(u.getFullname())
                        .setId(u.getId()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> users(@PathVariable String id){
        Optional<User> user = userService.fromId(id);

        return user.map(u -> ResponseEntity.ok(
                new UserResponse()
                        .setUsername(u.getUsername().getValue())
                        .setEmail(u.getEmail().getValue())
                        .setFullname(u.getFullname())
                        .setId(u.getId())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
