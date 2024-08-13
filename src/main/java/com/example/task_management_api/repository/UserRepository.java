package com.example.task_management_api.repository;

import com.example.task_management_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User save(User user);
}
