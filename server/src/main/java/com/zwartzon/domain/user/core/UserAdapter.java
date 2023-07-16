package com.zwartzon.domain.user.core;

import com.zwartzon.domain.user.core.model.GetUserByHandleQuery;
import com.zwartzon.domain.user.core.model.User;
import com.zwartzon.domain.user.core.ports.incoming.GetAllUsersPort;
import com.zwartzon.domain.user.core.ports.incoming.GetUserByHandlePort;
import com.zwartzon.domain.user.core.ports.outgoing.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserAdapter implements GetAllUsersPort, GetUserByHandlePort {
  private final UserRepository userRepository;

  public UserAdapter(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<User> handle() {
    return userRepository.getActiveUsers();
  }

  @Override
  public User handle(GetUserByHandleQuery query) {
    Optional<User> user = userRepository.getUserByHandle(query.userHandle());
    if (user.isPresent()) {
      return user.get();
    }

    throw new RuntimeException(
        "No user with handle [" + query.userHandle() + "] found."
    );
  }
}
