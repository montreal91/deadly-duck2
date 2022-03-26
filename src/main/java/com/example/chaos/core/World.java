package com.example.chaos.core;


import com.example.chaos.core.queries.WorldInfo;

import java.util.UUID;

class World implements GameWorld<PlayerContextDto, WorldInfo> {
  private int currentDay = 1;

  @Override
  public ActionResult acceptGameAction(Action action) {
    return new ActionResult(true);
  }

  @Override
  public PlayerContextDto getPlayerContext(UUID userId) {
    return new PlayerContextDto(currentDay);
  }

  @Override
  public WorldInfo getWorldInfo() {
    return new WorldInfo(currentDay);
  }

  @Override
  public void update() {
    currentDay++;
  }
}
