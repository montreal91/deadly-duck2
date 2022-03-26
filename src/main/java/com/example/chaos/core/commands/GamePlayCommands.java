package com.example.chaos.core.commands;

public interface GamePlayCommands {
  ActionResult makeAction(UserInfo userInfo, GameInfo gameInfo, Action action);
}
