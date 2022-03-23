package com.example.pokker.infrastructure;

import com.example.pokker.core.TournamentSeries;
import com.example.pokker.core.TournamentSeriesQueries;
import com.example.pokker.core.TournamentSeriesRepository;
import com.google.common.collect.ImmutableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class StubTournamentSeriesRepository
    implements TournamentSeriesRepository, TournamentSeriesQueries {
  private final Map<String, TournamentSeries> data = new HashMap<>();
  @Override
  public void save(TournamentSeries series) {
    data.put(series.getTitle(), series);
  }

  @Override
  public Optional<TournamentSeries> getTournamentSeriesByName(String name) {
    return Optional.ofNullable(data.get(name));
  }

  @Override
  public List<TournamentSeries> getAllTournamentSeries() {
    return ImmutableList.copyOf(data.values().stream().toList());
  }
}
