package com.example.chaos.webapi;

import com.example.chaos.core.GameService;
import com.example.chaos.core.queries.MetaGameQueries;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("game/chaos")
public class QueriesController {
  private final MetaGameQueries metaGameQueries;

  public QueriesController(GameService gameService) {
    this.metaGameQueries = gameService;
  }

  @GetMapping("/game/all/")
  public List<GameListViewDto> getAllGamesForOwner(@RequestBody String ownerHandle) {
    return metaGameQueries.getGamesForOwner(ownerHandle)
                          .stream()
                          .map(GameListViewDto::makeFromGame)
                          .collect(Collectors.toList());
  }
}
