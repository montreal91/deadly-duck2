package com.zwartzon.domain.user.core.model;

public class User {
  private final String handle;
  private String password;
  private String description;

  public User(String handle) {
    this.handle = handle;
  }

  public String getHandle() {
    return handle;
  }

  public String getPassword() {
    return password;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
