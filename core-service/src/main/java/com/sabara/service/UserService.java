package com.sabara.service;

import com.sabara.model.User;
import com.sabara.exception.ResourceNotFoundException;
import com.sabara.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;

  public User getUserById(long id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
  }

  public User addUser(User user) {
    return repository.save(user);
  }
}
