package com.example.user;

import com.example.user.core.UserRepository;
import com.example.user.core.UserService;
import com.example.user.infrastructure.BadUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UserConfiguration {
  @Bean
  public UserRepository userRepository() {
    return new BadUserRepository();
  }

  @Bean
  public UserService userService(UserRepository userRepository) {
    return new UserService(userRepository);
  }
}
