package com.sabara.service;

import com.sabara.exception.ResourceNotFoundException;
import com.sabara.model.entity.User;
import com.sabara.model.resource.UserResource;
import com.sabara.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserService {

  private final UserRepository repository;
  private final ModelMapper modelMapper;
  private final PasswordEncoder passwordEncoder;

  public UserResource getUserById(long id) {
    return repository.findById(id).map(
        user -> modelMapper.map(user, UserResource.class)
    ).orElseThrow(() -> new ResourceNotFoundException(id));
  }

  public User addUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return repository.save(user);
  }
}
