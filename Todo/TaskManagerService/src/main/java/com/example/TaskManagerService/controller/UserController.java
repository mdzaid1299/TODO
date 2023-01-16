package com.example.TaskManagerService.controller;

import com.example.TaskManagerService.domain.Task;
import com.example.TaskManagerService.domain.User;
import com.example.TaskManagerService.exception.TaskNotFoundException;
import com.example.TaskManagerService.exception.UserAlreadyExistException;
import com.example.TaskManagerService.exception.UserNotFoundException;
import com.example.TaskManagerService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v2")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user) throws UserAlreadyExistException {
        return new ResponseEntity(userService.register(user), HttpStatus.OK);
    }

    @PostMapping("/addtask/{email}")
    public ResponseEntity<?> addTask(@PathVariable String email, @RequestBody Task task) {
        return new ResponseEntity<>(userService.addTask(email, task), HttpStatus.OK);
    }

    @GetMapping("/getfromtask/{email}")
    public ResponseEntity<?> getFromTask(@PathVariable String email) {
        return new ResponseEntity<>(userService.getTask(email), HttpStatus.OK);
    }

    @GetMapping("/getalluser")
    public ResponseEntity<?> getAllUser() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @DeleteMapping("/deletetask/{email}/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable String email, @PathVariable String taskId) throws TaskNotFoundException {
        return new ResponseEntity<>(userService.deleteTask(email, taskId), HttpStatus.OK);
    }

    @PutMapping("/updatetask/{email}")
    public ResponseEntity<?> updateTaskForUser(@PathVariable String email, @RequestBody Task task) throws UserNotFoundException {
        ResponseEntity responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(userService.updateTaskForUser(email, task), HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
