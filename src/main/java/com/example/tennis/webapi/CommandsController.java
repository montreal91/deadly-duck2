package com.example.tennis.webapi;

import com.example.tennis.core.GameService;
import com.example.tennis.core.NewGameDto;
import com.example.user.core.User;
import com.google.common.collect.ImmutableSet;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;


@RestController
@RequestMapping("game/tennis")
public class CommandsController {
  private final GameService gameService;

  public CommandsController(GameService gameService) {
    this.gameService = gameService;
  }

  @PostMapping(value = "/new/game/", consumes={"application/json"})
  @ResponseBody
  public String createNewGame(@RequestBody NewGameInputDto newGameDto) {
    gameService.createGame(new NewGameDto(
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
