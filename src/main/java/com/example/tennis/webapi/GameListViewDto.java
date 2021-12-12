package com.example.tennis.webapi;

import com.example.tennis.core.Game;


public record GameListViewDto(
    String name,
    int participants,
    int maxSeasons,
    int currentSeason
) {
  static GameListViewDto makeFromGame(Game game) {
    return new GameListViewDto(
        game.getMetadata().getName(),
        game.getMetadata().getParticipants().size(),
        game.getWorldCommonData().maxSeasons(),
        game.getWorldCommonData().currentSeason()
    );
  }
}
