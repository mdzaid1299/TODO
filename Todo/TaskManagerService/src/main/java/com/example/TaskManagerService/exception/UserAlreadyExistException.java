package com.example.TaskManagerService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason="User already existed")
public class UserAlreadyExistException extends Exception{
}
