package com.example.tennis.core;

import java.util.Optional;
import java.util.UUID;

public interface GameRepository {
  void saveGame(Game game);
  Optional<Game> getGameByUuid(UUID gameUuid);
}
