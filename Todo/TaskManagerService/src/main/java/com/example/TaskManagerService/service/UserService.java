package com.example.TaskManagerService.service;

import com.example.TaskManagerService.domain.Task;
import com.example.TaskManagerService.domain.User;
import com.example.TaskManagerService.exception.TaskNotFoundException;
import com.example.TaskManagerService.exception.UserAlreadyExistException;
import com.example.TaskManagerService.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    User register(User user) throws UserAlreadyExistException;
    User addTask(String email, Task task);
    Boolean deleteTask(String email, String taskId) throws TaskNotFoundException;
    User updateTaskForUser(String email,Task task) throws UserNotFoundException;
    List<Task> getTask(String email);
    List<User> getAllUser();
}
