package com.example.pokker.core;

import java.util.UUID;


public class TournamentType {
  private final UUID id;
  private String title;

  public static TournamentType createNewTournamentType(String title) {
    return new TournamentType(UUID.randomUUID(), title);
  }

  private TournamentType(UUID id, String title) {
    this.id = id;
    this.title = title;
  }

  public UUID getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void changeTitle(final String newTitle) {
    title = newTitle;
  }
}
