package com.example.TaskManagerService.service;

import com.example.TaskManagerService.domain.Task;
import com.example.TaskManagerService.domain.User;
import com.example.TaskManagerService.exception.UserNotFoundException;
import com.example.TaskManagerService.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    public List<Task> findAllTask() {
        return taskRepository.findAll();
    }

    public Task updateTask(String taskId,Task task) {
        Task task2 = taskRepository.findById(taskId).get();
         if(task2.getTaskId() == task.getTaskId()){
                task2.setTaskName(task.getTaskName());
                task2.setDescription(task.getDescription());
                task2.setPriority(task.getPriority());
            }
         return taskRepository.save(task2);
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }
    public void deleteTask(String taskId) {
        taskRepository.deleteById(taskId);
    }
}
