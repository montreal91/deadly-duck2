package com.zwartzon.domain.user.core.ports.outgoing;

import com.zwartzon.domain.user.core.model.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository {
  Optional<User> getUserByHandle(String handle);
  List<User> getActiveUsers();
}
