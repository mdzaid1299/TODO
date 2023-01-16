package com.example.TaskManagerService.proxy;

import com.example.TaskManagerService.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-service",url = "http://localhost:8085")
public interface UserProxy {
    @PostMapping("api/v2/register")
    ResponseEntity saveUser(@RequestBody User user);
}
