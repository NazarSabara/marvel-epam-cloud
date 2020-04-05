package com.sabara.service;

import com.sabara.model.entity.User;
import com.sabara.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = repository.findByUsername(username).orElseThrow(() ->
            new UsernameNotFoundException(
                    "No user found with username: " + username)
        );

        return  new org.springframework.security.core.userdetails.User
                (user.getUsername(),
                    user.getPassword(), true, true,
                    true, true,
                    List.of(new SimpleGrantedAuthority("USER")));
    }
}
