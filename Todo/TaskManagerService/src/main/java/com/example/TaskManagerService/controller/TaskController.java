package com.example.TaskManagerService.controller;

import com.example.TaskManagerService.domain.Task;
import com.example.TaskManagerService.domain.User;
import com.example.TaskManagerService.exception.TaskNotFoundException;
import com.example.TaskManagerService.exception.UserAlreadyExistException;
import com.example.TaskManagerService.exception.UserNotFoundException;
import com.example.TaskManagerService.service.TaskService;
import com.example.TaskManagerService.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v2")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/alltask")
    public ResponseEntity<List<Task>> getAllTask() {
        List<Task> taskList = taskService.findAllTask();
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @PostMapping("/addtask")
    public ResponseEntity<?> addTask(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.addTask(task), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable String taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable String taskId, @RequestBody Task task) {
        return new ResponseEntity<>(taskService.updateTask(taskId, task), HttpStatus.CREATED);
    }
}
