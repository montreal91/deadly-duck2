package com.example.pokker.core;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Tournament {
  final UUID id;
  final String name;
  final TournamentSeries series;
  private final List<Object> results;
  private Status status;

  public Tournament(UUID id, String name, TournamentSeries series) {
    this.id = id;
    this.name = name;
    this.series = series;
    this.status = Status.DRAFT;
    this.results = new LinkedList<>();
  }

  List<Object> getResults() {
    return results;
  }

  Status getStatus() {
    return status;
  }
}
