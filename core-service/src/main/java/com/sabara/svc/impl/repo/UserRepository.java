package com.sabara.svc.impl.repo;

import com.sabara.svc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
