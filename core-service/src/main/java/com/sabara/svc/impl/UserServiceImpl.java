package com.sabara.svc.impl;

import com.sabara.exception.ResourceNotFoundException;
import com.sabara.svc.UserService;
import com.sabara.svc.impl.repo.UserRepository;
import com.sabara.svc.model.User;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  @Inject
  public UserServiceImpl(UserRepository repository){
    this.repository = repository;
  }

  public UserRepository getRepository() {
    return repository;
  }

  @Override
  public User getUserById(String id) {
    return getRepository().findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
  }

  @Override
  public User addUser(User user) {
    return getRepository().save(user);
  }
}
