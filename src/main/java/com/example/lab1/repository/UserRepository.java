package com.example.lab1.repository;

import com.example.lab1.beans.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByMail(String email);
    User findByMailAndPassword(String email, String password);
    Optional<User> findById(Long id);
}
