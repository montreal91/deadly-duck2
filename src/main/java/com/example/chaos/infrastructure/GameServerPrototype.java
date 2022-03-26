package com.example.chaos.infrastructure;

import com.example.chaos.core.commands.Action;
import com.example.chaos.core.Game;
import com.example.chaos.core.GameServer;
import com.example.chaos.core.commands.GameInfo;
import com.example.chaos.core.commands.UserInfo;

import java.util.Optional;

public class GameServerPrototype implements GameServer {
  @Override
  public void addGame(Game game) {
    System.out.println("Game '" + game.getMetadata().getName() + "' is added.");
  }

  @Override
  public void runGame(GameInfo gameInfo) {
    System.out.println("Game '" + gameInfo.name() + "' is started.");
  }

  @Override
  public Optional<Game> remove(GameInfo gameInfo) {
    System.out.println("Game '" + gameInfo.name() + "' is removed.");
    return Optional.empty();
  }

  @Override
  public Optional<Game> getGame(GameInfo gameInfo) {
    return Optional.empty();
  }

  @Override
  public void pushAction(GameInfo gameInfo, UserInfo userInfo, Action action) {
  }
}
