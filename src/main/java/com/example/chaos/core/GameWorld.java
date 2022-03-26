package com.example.chaos.core;

import com.example.chaos.core.commands.Action;
import com.example.chaos.core.commands.ActionResult;

import java.util.UUID;


public interface GameWorld<PC, WI> {
  ActionResult acceptGameAction(Action action);
  PC getPlayerContext(UUID userId);
  WI getWorldInfo();
  void update();
}
