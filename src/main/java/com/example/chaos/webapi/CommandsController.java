package com.example.chaos.webapi;

import com.example.chaos.core.commands.GameInfo;
import com.example.chaos.core.commands.GameManagementCommands;
import com.example.chaos.core.commands.NewGameData;
import com.example.chaos.core.commands.UserInfo;
import com.google.common.collect.ImmutableSet;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;


@RestController
@RequestMapping("game/chaos")
public class CommandsController {
  private final GameManagementCommands managementCommands;

  public CommandsController(GameManagementCommands gameService) {
    this.managementCommands = gameService;
  }

  @PostMapping(value = "/new/game/", consumes={"application/json"})
  @ResponseBody
  public String createNewGame(@RequestBody NewGameInputDto newGameDto) {
    managementCommands.createGame(new NewGameData(
        newGameDto.name(),
        new UserInfo(newGameDto.owner()),
        ImmutableSet.copyOf(
            newGameDto.participants()
                      .stream()
                      .map(UserInfo::new)
                      .collect(Collectors.toList())
        )
    ));
    return "OK";
  }

  @PostMapping(value = "/start/game/", consumes={"application/json"})
  public String startGame(@RequestBody StartGameInputDto startGameDto) {
    managementCommands.startGame(
        new UserInfo(startGameDto.ownerName()),
        new GameInfo(null, startGameDto.gameName())
    );
    return "OK";
  }

  @PostMapping(value = "/load/game/", consumes={"application/json"})
  public String loadGame(@RequestBody StartGameInputDto startGameDto) {
    managementCommands.loadGame(
        new UserInfo(startGameDto.ownerName()),
        new GameInfo(null, startGameDto.gameName())
    );
    return "OK";
  }
}
