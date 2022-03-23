package com.example.pokker.core;

import java.util.List;
import java.util.Optional;

public interface TournamentSeriesQueries {
  Optional<TournamentSeries> getTournamentSeriesByName(String name);
  List<TournamentSeries> getAllTournamentSeries();
}
