package com.sabara.controller;

import com.sabara.model.User;
import com.sabara.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService service;

  @Autowired
  public UserController(UserService service) {
    this.service = service;
  }

  @GetMapping(value = "/{id}")
  ResponseEntity<User> getUser(@PathVariable long id){
    return ResponseEntity.ok(service.getUserById(id));
  }

  @PostMapping
  ResponseEntity<User> createUser(@RequestBody User user){
    return new ResponseEntity<>(service.addUser(user), HttpStatus.CREATED);
  }
}
