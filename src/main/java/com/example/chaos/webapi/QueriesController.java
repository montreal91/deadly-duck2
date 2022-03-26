package com.example.chaos.webapi;

import com.example.chaos.core.GameService;
import com.example.chaos.core.queries.MetaGameQueries;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("game/chaos")
public class QueriesController {
  private final MetaGameQueries gameService;

  public QueriesController(GameService gameService) {
    this.gameService = gameService;
  }

  @GetMapping("/game/all/")
  List<GameListViewDto> getAllGamesForOwner(String ownerHandle) {
    return gameService.getGamesForOwner(ownerHandle)
                      .stream()
                      .map(GameListViewDto::makeFromGame)
                      .collect(Collectors.toList());
  }
}
