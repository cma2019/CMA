package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.user.User;
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
