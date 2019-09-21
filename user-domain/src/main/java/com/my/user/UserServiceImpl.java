package com.my.user;

import com.my.user.attributes.EMailAddress;
import com.my.user.attributes.Password;
import com.my.user.attributes.Username;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(User user) {
        if (StringUtils.isEmpty(user.getId())) {
            user.setId(UUID.randomUUID().toString());
            user.setPassword(Password.of(passwordEncoder.encode(user.getPassword().getValue())));
        }
        userRepository.save(user);
    }

    private boolean isExistMail(EMailAddress email) {
        return fromEmail(email).isPresent();
    }

    private boolean isExistUsername(Username username) {
        return fromUsername(username).isPresent();
    }

    @Override
    public Optional<User> fromUsername(Username username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> fromId(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> fromEmail(EMailAddress email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public List<User> users() {
        return userRepository.findAll();
    }

    @Override
    public void delete(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found! id=" + id));
        userRepository.delete(user);
    }

    @Override
    public void updateToken(String id, String token) {
        User user = fromId(id).orElseThrow(() -> new RuntimeException("Unexpected error. User not found. User Id : " + id));
        user.setToken(token);
        save(user);
    }
}
