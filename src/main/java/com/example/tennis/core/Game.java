package com.example.tennis.core;


public class Game {
  final Metadata metadata;
  final WorldState state;

  static Game makeGameFromCreateGameDto(NewGameDto dto) {
    return new Game(
        Metadata.makeFromCreateGameDto(dto),
        WorldState.makeFromCreateGameDto(dto)
    );
  }

  public Game(Metadata metadata, WorldState state) {
    this.metadata = metadata;
    this.state = state;
  }

  public ActionResult acceptAction(Action action) {
    return state.acceptAction(action);
  }

  public Metadata getMetadata() {
    return new Metadata(metadata);
  }

  public WorldCommonData getWorldCommonData() {
    return new WorldCommonData(
        state.getCurrentDay(),
        state.getCurrentSeason(),
        state.getMaxSeasons()
    );
  }
}
