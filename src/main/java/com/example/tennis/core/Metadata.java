package com.example.tennis.core;

import com.example.user.core.User;
import com.google.common.collect.ImmutableList;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class Metadata {
  private UUID id;
  private User owner;
  private final String name;
  private final Map<String, User> participants = new HashMap<>();
  private final List<Action> actions = new LinkedList<>();
  private boolean isDeleted = false;

  public Metadata(String name) {
    this.name = name;
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

  public List<Action> getActions() {
    return ImmutableList.copyOf(actions);
  }

  public void setOwner(User owner) {
    this.owner = owner;
    participants.put(owner.getHandle(), owner);
  }

  public void addParticipant(User user) {
    participants.put(user.getHandle(), user);
  }

  public void addAction(Action action) {
    actions.add(action);
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
