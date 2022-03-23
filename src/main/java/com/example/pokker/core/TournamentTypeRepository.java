package com.example.pokker.core;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TournamentTypeRepository {
  void save(TournamentType tournamentType);

  List<TournamentType> getAllTournamentTypes();
  Optional<TournamentType> getTournamentTypeByTitle(String title);
  Optional<TournamentType> getTournamentTypeById(UUID id);
}
