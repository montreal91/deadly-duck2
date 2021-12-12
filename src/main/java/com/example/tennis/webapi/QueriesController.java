package com.example.tennis.webapi;

import com.example.tennis.core.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("game/tennis")
public class QueriesController {
  private final GameService gameService;

  public QueriesController(GameService gameService) {
    this.gameService = gameService;
  }

  @GetMapping("/game/all/")
  List<GameListViewDto> getAllGamesForOwner(String ownerHandle) {
    return gameService
        .getGamesForOwner(ownerHandle)
        .stream()
        .map(GameListViewDto::makeFromGame)
        .collect(Collectors.toList());
  }
}
