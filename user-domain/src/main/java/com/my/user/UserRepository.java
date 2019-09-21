package com.my.user;

import com.my.user.attributes.EMailAddress;
import com.my.user.attributes.Username;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByUsername(Username username);
    Optional<User> findByEmail(EMailAddress email);
    List<User> findAll();
}
