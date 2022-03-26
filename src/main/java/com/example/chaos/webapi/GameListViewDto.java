package com.example.chaos.webapi;

import com.example.chaos.core.queries.GameMetaData;


public record GameListViewDto(
    String name,
    int participants,
    int currentDay
) {
  static GameListViewDto makeFromGame(GameMetaData metaData) {
    return new GameListViewDto(
        metaData.name(),
        metaData.participants().size(),
        metaData.worldInfo().currentDay()
    );
  }
}
