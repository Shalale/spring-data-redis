package com.springdataredis.controller;

import com.springdataredis.model.dto.UserDto;
import com.springdataredis.model.dto.UserRequest;
import com.springdataredis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private final UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserRequest request){
        return service.createUser(request);
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id){
       return service.getUser(id);
    }

    @GetMapping("/name")
    public Long getUserId(@RequestParam String username){
        return service.getUserId(username);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }
}
