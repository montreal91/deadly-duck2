package com.example.chaos;

import com.example.chaos.core.GameRepository;
import com.example.chaos.core.GameServer;
import com.example.chaos.core.GameService;
import com.example.chaos.infrastructure.GameRepositoryPrototype;
import com.example.chaos.infrastructure.GameServerPrototype;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class Config {
  @Bean
  GameRepository gameRepository() {
    return new GameRepositoryPrototype();
  }

  @Bean
  GameServer gameServer() {
    return new GameServerPrototype();
  }

  @Bean
  GameService gameService(GameRepository repository, GameServer server) {
    return new GameService(repository, server);
  }
}
