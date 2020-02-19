package com.sabara.svc;

import com.sabara.svc.model.User;

public interface UserService {

  User getUserById(String id);

  User addUser(User user);
}
