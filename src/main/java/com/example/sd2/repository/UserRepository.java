package com.example.sd2.repository;

import com.example.sd2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User save(User user);

	User findByUsername(String username);

}