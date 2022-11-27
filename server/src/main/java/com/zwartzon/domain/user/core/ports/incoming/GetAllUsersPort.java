package com.zwartzon.domain.user.core.ports.incoming;

import com.zwartzon.domain.user.core.model.User;

import java.util.List;

public interface GetAllUsersPort {
  List<User> handle();
}
