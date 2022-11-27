package com.zwartzon.domain.user.application;

import com.zwartzon.domain.user.core.model.User;


public record ListUserDto(String handle) {

  static ListUserDto fromEntity(User entity) {
    return new ListUserDto(entity.getHandle());
  }
}
