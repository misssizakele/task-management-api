package com.example.task_management_api.controller;

import com.example.task_management_api.dto.TaskDTO;
import com.example.task_management_api.model.Task;
import com.example.task_management_api.model.User;
import com.example.task_management_api.repository.UserRepository;
import com.example.task_management_api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO) {
        Task task = taskService.createTask(taskDTO);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping("/getTasks")
    public List<Task> getTasks(@RequestParam(value = "status", required = false) String status) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByUsername(userDetails.getUsername());

        return taskService.getTasksForUser(currentUser, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        Task updatedTask = taskService.updateTask(id, taskDTO);
        return ResponseEntity.ok(updatedTask);
    }
}
