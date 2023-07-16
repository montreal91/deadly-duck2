package com.zwartzon.domain.user.application;

import com.zwartzon.domain.user.core.model.User;

public record UserDetailsDto(String handle, String description) {
  static UserDetailsDto fromEntity(User entity) {
    return new UserDetailsDto(entity.getHandle(), entity.getDescription());
  }
}
