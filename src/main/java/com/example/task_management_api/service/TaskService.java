package com.example.task_management_api.service;

import com.example.task_management_api.dto.TaskDTO;
import com.example.task_management_api.model.Task;
import com.example.task_management_api.model.User;
import com.example.task_management_api.repository.TaskRepository;
import com.example.task_management_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public Task createTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());
        task.setPriority(taskDTO.getPriority());

        // Find the assigned user by ID
        User assignedUser = userRepository.findById(taskDTO.getAssignedUserId()).orElse(null);
        task.setAssignedUser(assignedUser);

        return taskRepository.save(task);
    }

    public List<Task> getTasksForUser(User user, String status) {
        if (status != null && !status.isEmpty()) {
            return taskRepository.findByAssignedUserAndStatus(user, status);
        } else {
            return taskRepository.findByAssignedUser(user);
        }
    }

    public Task updateTask(Long taskId, TaskDTO taskDTO) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (!taskOptional.isPresent()) {
            throw new RuntimeException("Task not found");
        }

        Task task = taskOptional.get();

        if (taskDTO.getTitle() != null) {
            task.setTitle(taskDTO.getTitle());
        }

        if (taskDTO.getDescription() != null) {
            task.setDescription(taskDTO.getDescription());
        }

        if (taskDTO.getDueDate() != null) {
            task.setDueDate(taskDTO.getDueDate());
        }

        if (taskDTO.getPriority() != null) {
            task.setPriority(taskDTO.getPriority());
        }

        if (taskDTO.getAssignedUserId() != null) {
            Optional<User> userOptional = userRepository.findById(taskDTO.getAssignedUserId());
            if (!userOptional.isPresent()) {
                throw new RuntimeException("User not found");
            }
            task.setAssignedUser(userOptional.get());
        }

        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new RuntimeException("Task not found");
        }
        taskRepository.deleteById(taskId);
    }
}
