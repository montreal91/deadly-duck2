package com.example.chaos.core.commands;

import java.util.UUID;

public interface MetaGameCommands {
  void createGame(NewGameData dto);
  void loadGame(UUID gameUuid);
  void exitGame(UUID gameUid);
  void deleteGame(UUID gameId);
}
