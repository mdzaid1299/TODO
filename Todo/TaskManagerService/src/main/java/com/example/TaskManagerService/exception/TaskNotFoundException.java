package com.example.TaskManagerService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Task Not found exception")
public class TaskNotFoundException extends Exception{

}
