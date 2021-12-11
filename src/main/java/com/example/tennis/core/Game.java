package com.example.tennis.core;


public class Game {
  final Metadata metadata;
  final WorldState state;

  public Game(Metadata metadata, WorldState state) {
    this.metadata = metadata;
    this.state = state;
  }

  public ActionResult acceptAction(Action action) {
    ActionResult result = state.acceptAction(action);
    if (result.actionAccepted) {
      metadata.addAction(action);
    }
    return result;
  }
}
