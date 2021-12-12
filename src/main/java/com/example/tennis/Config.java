package com.example.tennis;

import com.example.tennis.core.GameRepository;
import com.example.tennis.core.GameService;
import com.example.tennis.infrastructure.GameRepositoryPrototype;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
  @Bean
  GameRepository gameRepository() {
    return new GameRepositoryPrototype();
  }

  @Bean
  GameService gameService(GameRepository repository) {
    return new GameService(repository);
  }
}
