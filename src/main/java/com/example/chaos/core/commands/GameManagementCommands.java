package com.example.chaos.core.commands;

import java.util.UUID;

public interface GameManagementCommands {
  void createGame(NewGameData dto);
  void loadGame(GameInfo gameUuid);
  void exitGame(GameInfo gameUid);
  void deleteGame(GameInfo gameId);
}
