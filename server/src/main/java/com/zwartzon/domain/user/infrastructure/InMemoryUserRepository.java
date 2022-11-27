package com.zwartzon.domain.user.infrastructure;

import com.zwartzon.domain.user.core.model.User;
import com.zwartzon.domain.user.core.ports.outgoing.UserRepository;
import com.google.common.collect.ImmutableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class InMemoryUserRepository implements UserRepository {
  private static final Map<String, User> users = createUsers();

  @Override
  public Optional<User> getUserByHandle(String handle) {
    return Optional.ofNullable(users.get(handle));
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
