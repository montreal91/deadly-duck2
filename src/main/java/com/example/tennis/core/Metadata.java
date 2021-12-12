package com.example.tennis.core;

import com.example.user.core.User;
import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class Metadata {
  private UUID id;
  private User owner;
  private final String name;
  private final Map<String, User> participants = new HashMap<>();
  private boolean isDeleted = false;

  static Metadata makeFromCreateGameDto(NewGameDto dto) {
    Metadata metadata = new Metadata(dto.name());
    metadata.setOwner(dto.owner());
    metadata.addAllParticipants(dto.participants());
    metadata.setId(UUID.randomUUID());
    return metadata;
  }

  public Metadata(String name) {
    this.name = name;
  }

  Metadata(Metadata otherData) {
    this.id = otherData.id;
    this.owner = otherData.owner;
    this.name = otherData.name;
    this.participants.putAll(otherData.participants);
    this.isDeleted = otherData.isDeleted;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public User getOwner() {
    return owner;
  }

  public List<User> getParticipants() {
    return ImmutableList.copyOf(participants.values());
  }

  public void setOwner(User owner) {
    this.owner = owner;
    participants.put(owner.getHandle(), owner);
  }

  public void addParticipant(User user) {
    participants.put(user.getHandle(), user);
  }

  public void addAllParticipants(Collection<User> users) {
    for (var user : users) {
      participants.put(user.getHandle(), user);
    }
  }

  public void removeParticipant(String handle) {
    participants.remove(handle);
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  public void delete() {
    isDeleted = true;
  }

  public String getName() {
    return name;
  }
}
