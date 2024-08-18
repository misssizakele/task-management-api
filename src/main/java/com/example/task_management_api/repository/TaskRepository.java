package com.example.task_management_api.repository;

import com.example.task_management_api.model.Task;
import com.example.task_management_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignedUserAndStatus(User assignedUser, String status);
    List<Task> findByAssignedUser(User assignedUser);
}
