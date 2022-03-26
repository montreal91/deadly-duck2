package com.example.chaos.core;

import com.example.chaos.core.commands.NewGameData;
import com.example.chaos.core.queries.WorldInfo;

public class Game {
  final Metadata metadata;
  final GameWorld<PlayerContextDto, WorldInfo> world;

  static Game makeGameFromCreateGameDto(NewGameData dto) {
    return new Game(
        Metadata.makeFromCreateGameDto(dto),
        new World()
    );
  }

  public Game(Metadata metadata, GameWorld<PlayerContextDto, WorldInfo> state) {
    this.metadata = metadata;
    this.world = state;
  }

  public ActionResult acceptAction(Action action) {
    return world.acceptGameAction(action);
  }

  public Metadata getMetadata() {
    return new Metadata(metadata);
  }

  WorldInfo getWorldInfo() {
    return world.getWorldInfo();
  }
}
