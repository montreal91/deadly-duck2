package com.example.chaos.infrastructure;

import com.example.chaos.core.Emitter;
import com.example.chaos.core.PlayerContextDto;

public class ConsoleEmitter implements Emitter<PlayerContextDto> {
  @Override
  public void emit(PlayerContextDto message) {
    System.out.println(message);
  }
}
