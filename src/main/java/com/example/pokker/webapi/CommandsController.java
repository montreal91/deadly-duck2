package com.example.pokker.webapi;

import com.example.pokker.core.TournamentService;
import com.example.pokker.core.datatransfer.NewTournamentSeriesDto;
import com.example.pokker.core.datatransfer.NewTournamentTypeDto;
import com.example.pokker.webapi.datatransfer.NewTournamentSeriesInput;
import com.example.pokker.webapi.datatransfer.NewTournamentTypeInput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController("PokkerCommandsController")
@RequestMapping("api/pokker")
public class CommandsController {
  private final TournamentService tournamentService;

  public CommandsController(TournamentService tournamentService) {
    this.tournamentService = tournamentService;
  }

  @PostMapping(value = "/create-tournament-type/")
  public void createTournamentType(@RequestBody NewTournamentTypeInput input) {
    var res = tournamentService.createNewTournamentType(
        new NewTournamentTypeDto(input.name())
    );
    System.out.println(res.toString() + " " + input);
  }

  @PostMapping(value = "/create-tournament-series/")
  public void createTournamentSeries(@RequestBody NewTournamentSeriesInput input) {
    var res = tournamentService.createNewTournamentSeries(new NewTournamentSeriesDto(
        UUID.fromString(input.tournamentTypeId()),
        input.table(),
        input.minPlayers(),
        input.maxPlayers(),
        input.uniqueName()
    ));
    System.out.println(res.toString() + " " + input);
  }

  @PostMapping(value = "/create-tournament/")
  public void createTournament() {}

  @PostMapping(value = "/update-tournament/")
  public void updateTournament() {}

  @PostMapping(value = "/enroll/")
  public void enrollOnTournament() {}

  @PostMapping(value = "/unroll/")
  public void unrollFromTournament() {}

  @PostMapping(value = "/make-game-action/")
  public void makeGameAction() {}
}
