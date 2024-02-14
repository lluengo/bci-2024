package com.lluengo.bci.repository;

import java.util.Optional;

import com.lluengo.bci.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Transactional
    Optional<User> findByUsername(String username); 
}
