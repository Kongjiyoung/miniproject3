package com.many.miniproject1.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserJPARepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
