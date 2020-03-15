package com.sabara.controller;

import com.sabara.model.entity.User;
import com.sabara.model.resource.UserResource;
import com.sabara.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserController {

  private final UserService service;

  @GetMapping("/{id}")
  ResponseEntity<UserResource> getUser(@PathVariable long id) {
    return ResponseEntity.ok(service.getUserById(id));
  }

  @PostMapping
  ResponseEntity<User> createUser(@RequestBody User user) {
    return ResponseEntity.ok(service.addUser(user));
  }
}
