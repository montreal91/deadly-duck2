package com.example.user.infrastructure;

import com.example.user.core.User;
import com.example.user.core.UserRepository;
import com.google.common.collect.ImmutableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BadUserRepository implements UserRepository {
  private static final Map<String, User> users = createUsers();

  @Override
  public User getUserByHandle(String handle) {
    if (users.containsKey(handle)) {
      return users.get(handle);
    }
    throw new RuntimeException("No user with handle '" + handle + "' found");
  }

  @Override
  public List<User> getActiveUsers() {
    return ImmutableList.copyOf(users.values());
  }

  private static User createUser(String handle, String password) {
    User u = new User(handle);
    u.setPassword(password);
    return u;
  }

  private static Map<String, User> createUsers() {
    Map<String, User> defaultUsers = new HashMap<>();
    defaultUsers.put(
        "montreal",
        createUser("montreal", "montreal")
    );
    defaultUsers.put(
        "nos_habebit_humus",
        createUser("nos_habebit_humus", "nos_habebit_humus")
    );
    defaultUsers.put(
        "LeniDuke",
        createUser("LeniDuke", "LeniDuke")
    );
    return defaultUsers;
  }
}
