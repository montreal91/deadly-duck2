package com.example.chaos.core;

import com.example.chaos.core.commands.NewGameData;
import com.example.chaos.core.commands.UserInfo;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class Metadata {
  private UUID id;
  private UserInfo owner;
  private final String name;
  private final Map<String, UserInfo> participants = new HashMap<>();
  private boolean isDeleted = false;

  static Metadata makeFromCreateGameDto(NewGameData dto) {
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

  public UserInfo getOwner() {
    return owner;
  }

  public List<UserInfo> getParticipants() {
    return List.copyOf(participants.values());
  }

  public void setOwner(UserInfo owner) {
    this.owner = owner;
    participants.put(owner.name(), owner);
  }

  public void addParticipant(UserInfo user) {
    participants.put(user.name(), user);
  }

  public void addAllParticipants(Collection<UserInfo> users) {
    for (var user : users) {
      participants.put(user.name(), user);
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
