package com.example.chaos.core;

import java.util.UUID;

public abstract class Action {
  final UUID gameUuid;

  protected Action(UUID gameUuid) {
    this.gameUuid = gameUuid;
  }
}
