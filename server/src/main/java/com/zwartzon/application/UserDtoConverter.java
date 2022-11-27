package com.zwartzon.application;

import com.zwartzon.api.grpc.UserListView;
import com.zwartzon.domain.user.application.ListUserDto;


public class UserDtoConverter {
  public UserListView dtoToView(ListUserDto dto) {
    return UserListView.newBuilder()
                       .setHandle(dto.handle())
                       .build();
  }
}
