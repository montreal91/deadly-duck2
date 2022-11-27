package com.zwartzon.domain.user.application;

import com.zwartzon.domain.user.core.model.GetUserByHandleQuery;
import com.zwartzon.domain.user.core.ports.incoming.GetAllUsersPort;
import com.zwartzon.domain.user.core.ports.incoming.GetUserByHandlePort;

import java.util.List;
import java.util.stream.Collectors;


public class App {
  private final GetAllUsersPort getAllUsersPort;
  private final GetUserByHandlePort getUserByHandlePort;

  public App(
      GetAllUsersPort getAllUsersPort,
      GetUserByHandlePort getUserByHandlePort
  ) {
    this.getAllUsersPort = getAllUsersPort;
    this.getUserByHandlePort = getUserByHandlePort;
  }

  public List<ListUserDto> getAllUsers() {
    return getAllUsersPort.handle()
                          .stream()
                          .map(ListUserDto::fromEntity)
                          .collect(Collectors.toList());
  }

  public UserDetailsDto getUserByHandle(String handle) {
    return UserDetailsDto.fromEntity(
        getUserByHandlePort.handle(new GetUserByHandleQuery(handle))
    );
  }
}
