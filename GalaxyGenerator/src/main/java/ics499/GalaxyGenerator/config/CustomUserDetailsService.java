package ics499.GalaxyGenerator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ics499.GalaxyGenerator.model.User;
import ics499.GalaxyGenerator.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService { // This class is needed in WebSecurityConfig.java
  @Autowired
  private UserRepository repo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = repo.findByEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    return new CustomUserDetails(user);
  }
}
