package com.question.UserAuthenticationService.controller;

import com.question.UserAuthenticationService.domain.User;
import com.question.UserAuthenticationService.exception.UserNotFoundException;
import com.question.UserAuthenticationService.service.SecurityTokenGenerator;
import com.question.UserAuthenticationService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1")
public class UserController {
    private ResponseEntity responseEntity;
    private SecurityTokenGenerator securityTokenGenerator;
    private UserService userService;

    @Autowired
    public UserController(UserService userService,SecurityTokenGenerator securityTokenGenerator)
    {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity saveUser(@RequestBody User user)
    {
        User createUser=userService.saveUser(user);
        return responseEntity = new ResponseEntity("Added Successfully",HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody User user)throws UserNotFoundException
    {
        ResponseEntity responseEntity = null;
        Map<String,String> map = null;
        try {
            User user1 = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
            if (user1.getEmail().equals(user.getEmail()))
            {
                map = securityTokenGenerator.generateToken(user);
            }
            responseEntity = new ResponseEntity<>(map,HttpStatus.OK);
        }catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity<>("Try After Some Time",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
//        try
//        {
//            User userObj=userService.findByEmailAndPassword(user.getEmail(),user.getPassword());
//            responseEntity=new ResponseEntity(HttpStatus.OK);
//        }
//        catch (UserNotFoundException userNotFoundException)
//        {
//            throw new UserNotFoundException();
//        }
//        catch (Exception exception)
//        {
//            responseEntity=new ResponseEntity("Try Again",HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return responseEntity;
    }
}
