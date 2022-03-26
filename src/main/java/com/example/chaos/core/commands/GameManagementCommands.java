package com.example.chaos.core.commands;

public interface GameManagementCommands {
  void createGame(NewGameData dto);
  void loadGame(UserInfo userInfo, GameInfo gameInfo);
  void exitGame(GameInfo gameInfo);
  void startGame(UserInfo userInfo, GameInfo gameInfo);
  void deleteGame(GameInfo gameId);
}
