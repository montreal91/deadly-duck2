package com.zwartzon.user.core;

import java.util.List;

public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User getUserByHandle(String handle) {
    return userRepository.getUserByHandle(handle);
  }

  public List<User> getActiveUsers() {
    return userRepository.getActiveUsers();
  }
}
