package com.example.user.webapi;

import com.example.user.core.User;


public class ListUserDto {
  public final String handle;

  public ListUserDto(String handle) {
    this.handle = handle;
  }

  static ListUserDto fromEntity(User entity) {
    return new ListUserDto(entity.getHandle());
  }
}
