package com.sabara.service;

import com.sabara.model.entity.User;
import com.sabara.exception.ResourceNotFoundException;
import com.sabara.model.resource.UserResource;
import com.sabara.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserService {

  private final UserRepository repository;

  public UserResource getUserById(long id) {
    return repository.findById(id).map(
        user -> UserResource.builder()
            .username(user.getUsername())
            .email(user.getEmail())
            .birthDate(user.getBirthDate())
            .info(user.getInfo())
            .build()
    ).orElseThrow(() -> new ResourceNotFoundException(id));
  }

  public User addUser(User user) {
    return repository.save(user);
  }
}
