package com.sabara.controller;

import com.sabara.svc.UserService;
import com.sabara.svc.model.User;
import javax.inject.Inject;
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

  @Inject
  public UserController(UserService service) {
    this.service = service;
  }

  public UserService getService() {
    return service;
  }

  @GetMapping(value = "/{id}", produces = "application/json")
  ResponseEntity<User> getUser(@PathVariable String id){
    return new ResponseEntity<>(getService().getUserById(id), HttpStatus.OK);
  }

  @PostMapping(consumes = "application/json")
  ResponseEntity<User> createUser(@RequestBody User user){
    return new ResponseEntity<>(getService().addUser(user), HttpStatus.CREATED);
  }
}
