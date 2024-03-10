package com.my_ecommerce.my_ecommerce.security.services;

import com.my_ecommerce.my_ecommerce.domain.User01;
import com.my_ecommerce.my_ecommerce.repos.UserRepository01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository01 userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User01 user = userRepository.findById(username)
        .orElseThrow(() -> new UsernameNotFoundException("User01 Not Found with username: " + username));

    return UserDetailsImpl.build(user);
  }

}
