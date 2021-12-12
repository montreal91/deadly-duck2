package com.example.tennis.core;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface GameRepository {
  void saveGame(Game game);
  Optional<Game> getGameByUuid(UUID gameUuid);
  List<Game> getAllGamesByOwner(String userHandle);
  Optional<Game> getGameByOwnerAndHandle(String userHandle, String gameName);
}
