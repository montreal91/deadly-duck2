package com.zwartzon.user.core;

public class User {
  private final String handle;
  private String password;

  public User(String handle) {
    this.handle = handle;
  }

  public String getHandle() {
    return handle;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
