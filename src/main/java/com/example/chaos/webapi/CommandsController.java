package com.example.chaos.webapi;

import com.example.chaos.core.commands.GameManagementCommands;
import com.example.chaos.core.commands.NewGameData;
import com.example.user.core.User;
import com.google.common.collect.ImmutableSet;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;


@RestController
@RequestMapping("game/chaos")
public class CommandsController {
  private final GameManagementCommands gameService;

  public CommandsController(GameManagementCommands gameService) {
    this.gameService = gameService;
  }

  @PostMapping(value = "/new/game/", consumes={"application/json"})
  @ResponseBody
  public String createNewGame(@RequestBody NewGameInputDto newGameDto) {
    gameService.createGame(new NewGameData(
        newGameDto.name(),
        new User(newGameDto.ownerHandle()),
        ImmutableSet.copyOf(
            newGameDto.participants()
                      .stream()
                      .map(User::new)
                      .collect(Collectors.toList())
        ),
        newGameDto.maxSeasons()
    ));
    return "OK";
  }
}
