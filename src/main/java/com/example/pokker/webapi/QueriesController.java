package com.example.pokker.webapi;

import com.example.pokker.core.TournamentSeriesQueries;
import com.example.pokker.core.TournamentService;
import com.example.pokker.webapi.datatransfer.TournamentSeriesListView;
import com.example.pokker.webapi.datatransfer.TournamentTypeDetailsView;
import com.example.pokker.webapi.datatransfer.TournamentTypeListView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController("PokkerQueriesController")
@RequestMapping("api/pokker")
public class QueriesController {
  private final TournamentService tournamentService;
  private final TournamentSeriesQueries tournamentSeriesQueries;

  public QueriesController(
      TournamentService tournamentService,
      TournamentSeriesQueries tournamentSeriesQueries
  ) {
    this.tournamentService = tournamentService;
    this.tournamentSeriesQueries = tournamentSeriesQueries;
  }

  @GetMapping("/all-tournament-types/")
  public List<TournamentTypeListView> getAllTournamentTypes() {
    var types = tournamentService.getAllTournamentTypes();
    return types.stream().map(
        type -> new TournamentTypeListView(type.getTitle())
    ).collect(Collectors.toList());
  }

  @GetMapping("/tournament-type/{title}/")
  public TournamentTypeDetailsView getTournamentType(@PathVariable String title) {
    var type = tournamentService.getTournamentType(title);
    return type.map(TournamentTypeDetailsView::fromTournamentType).orElse(null);
  }

  @GetMapping("/all-tournament-series/")
  public List<TournamentSeriesListView> getAllTournamentSeries() {
    return tournamentSeriesQueries.getAllTournamentSeries()
        .stream()
        .map(TournamentSeriesListView::fromTournamentSeries)
        .collect(Collectors.toList());
  }
}
