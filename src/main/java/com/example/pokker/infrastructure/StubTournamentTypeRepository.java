package com.example.pokker.infrastructure;

import com.example.pokker.core.TournamentType;
import com.example.pokker.core.TournamentTypeRepository;
import com.google.common.collect.ImmutableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class StubTournamentTypeRepository implements TournamentTypeRepository {

  private final Map<String, TournamentType> data = new HashMap<>();

  @Override
  public void save(TournamentType tournamentType) {
    data.put(tournamentType.getTitle(), tournamentType);
  }

  @Override
  public List<TournamentType> getAllTournamentTypes() {
    return ImmutableList.copyOf(data.values().stream().toList());
  }

  @Override
  public Optional<TournamentType> getTournamentTypeByTitle(String title) {
    return Optional.ofNullable(data.get(title));
  }

  @Override
  public Optional<TournamentType> getTournamentTypeById(UUID id) {
    return data.values().stream().filter(tt -> tt.getId().equals(id)).findFirst();
  }
}
