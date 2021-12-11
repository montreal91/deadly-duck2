package com.example.tennis.core;

import com.example.user.core.User;
import com.google.common.collect.ImmutableSet;


public class CreateGameDto {
  final String name;
  final User owner;
  final ImmutableSet<User> participants;

  public CreateGameDto(
      String name,
      User owner,
      ImmutableSet<User> participants
  ) {
    this.name = name;
    this.owner = owner;
    this.participants = participants;
  }
}
