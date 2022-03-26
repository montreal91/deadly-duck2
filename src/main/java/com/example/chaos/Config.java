package com.example.chaos;

import com.example.chaos.core.GameRepository;
import com.example.chaos.core.GameService;
import com.example.chaos.infrastructure.GameRepositoryPrototype;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class Config {
  @Bean
  GameRepository gameRepository() {
    return new GameRepositoryPrototype();
  }

  @Bean
  GameService gameService(GameRepository repository) {
    return new GameService(repository);
  }
}
