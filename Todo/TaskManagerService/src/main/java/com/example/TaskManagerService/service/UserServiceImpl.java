package com.example.TaskManagerService.service;

import com.example.TaskManagerService.domain.Task;
import com.example.TaskManagerService.domain.User;
import com.example.TaskManagerService.exception.TaskNotFoundException;
import com.example.TaskManagerService.exception.UserAlreadyExistException;
import com.example.TaskManagerService.exception.UserNotFoundException;
import com.example.TaskManagerService.proxy.UserProxy;
import com.example.TaskManagerService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private UserProxy userAuthProxy;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,UserProxy userAuthProxy) {
        this.userRepository = userRepository;
        this.userAuthProxy=userAuthProxy;
    }
    @Override
    public User register(User user) throws UserAlreadyExistException {
        if (userRepository.findById(user.getEmail()).isPresent())
        {
            throw new UserAlreadyExistException();
        }
        ResponseEntity response = userAuthProxy.saveUser(user);

        return userRepository.save(user);
    }

    @Override
    public User addTask(String email, Task task) {
        User user = userRepository.findById(email).get();
        if(user.getTaskList() == null) {
            user.setTaskList(Arrays.asList(task));
        }
        else {
            user.getTaskList().add(task);
        }
        return userRepository.save(user);
    }

    @Override
    public Boolean deleteTask(String email, String taskId) throws TaskNotFoundException {
        User user = userRepository.findById(email).get();
        user.getTaskList().removeIf(li -> li.getTaskId().equals(taskId));
        userRepository.save(user);
        return true;
    }

    @Override
    public User updateTaskForUser(String email, Task task) throws UserNotFoundException {
        if(userRepository.findById(email).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(email).get();
        List<Task> userTaskList = user.getTaskList();
        Iterator<Task> iterator = userTaskList.iterator();
        while (iterator.hasNext()){
            Task task1 = iterator.next();
            if(task1.getTaskId() == task.getTaskId()){
                task1.setTaskName(task.getTaskName());
                task1.setDescription(task.getDescription());
                task1.setPriority(task.getPriority());
            }
        }
        user.setTaskList(userTaskList);
        return userRepository.save(user);
    }

    @Override
    public List<Task> getTask(String email) {
        return userRepository.findById(email).get().getTaskList();
    }
    @Override
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }
}
