package com.question.UserAuthenticationService.service;

import com.question.UserAuthenticationService.domain.User;
import com.question.UserAuthenticationService.exception.UserNotFoundException;

public interface UserService {
    User saveUser(User user);

    User findByEmailAndPassword(String email,String password) throws UserNotFoundException;

}
