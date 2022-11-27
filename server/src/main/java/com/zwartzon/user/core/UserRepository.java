package com.zwartzon.user.core;

import java.util.List;


public interface UserRepository {
  User getUserByHandle(String handle);
  List<User> getActiveUsers();
}
