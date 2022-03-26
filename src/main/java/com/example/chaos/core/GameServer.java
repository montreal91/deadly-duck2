package com.example.chaos.core;

import com.example.chaos.core.commands.Action;
import com.example.chaos.core.commands.GameInfo;
import com.example.chaos.core.commands.UserInfo;

import java.util.Optional;

public interface GameServer {
  void addGame(Game game);
  void runGame(GameInfo gameInfo);
  Optional<Game> remove(GameInfo gameInfo);
  Optional<Game> getGame(GameInfo gameInfo);

  void pushAction(GameInfo gameInfo, UserInfo userInfo, Action action);
}
