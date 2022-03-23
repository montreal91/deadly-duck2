package com.example.pokker.core;

import com.example.pokker.core.datatransfer.NewTournamentTypeDto;
import com.example.pokker.core.datatransfer.NewTournamentSeriesDto;

import java.util.List;
import java.util.Optional;


public class TournamentService {
  private final TournamentTypeRepository tournamentTypeRepository;
  private final TournamentSeriesRepository tournamentSeriesRepository;
  private final TournamentSeriesQueries tournamentSeriesQueries;

  public TournamentService(
      TournamentTypeRepository tournamentTypeRepository,
      TournamentSeriesRepository tournamentSeriesRepository,
      TournamentSeriesQueries tournamentSeriesQueries) {
    this.tournamentTypeRepository = tournamentTypeRepository;
    this.tournamentSeriesRepository = tournamentSeriesRepository;
    this.tournamentSeriesQueries = tournamentSeriesQueries;
  }

  public ActionResult createNewTournamentType(NewTournamentTypeDto dto) {
    if (checkIfNewTypeValid(dto)) {
      tournamentTypeRepository.save(
          TournamentType.createNewTournamentType(dto.title())
      );
      return ActionResult.accepted();
    }
    return ActionResult.rejected();
  }

  public ActionResult createNewTournamentSeries(NewTournamentSeriesDto dto) {
    var oType = tournamentTypeRepository.getTournamentTypeById(dto.tournamentTypeId());
    if (oType.isEmpty()) {
      return ActionResult.rejected();
    }
    if (checkIfNewSeriesValid(dto)) {
      tournamentSeriesRepository.save(
          TournamentSeries.createNewTournamentSeries(dto, oType.get())
      );
      return ActionResult.accepted();
    }
    return ActionResult.rejected();
  }

  public List<TournamentType> getAllTournamentTypes() {
    return tournamentTypeRepository.getAllTournamentTypes();
  }

  public Optional<TournamentType> getTournamentType(String title) {
    return tournamentTypeRepository.getTournamentTypeByTitle(title);
  }

  private boolean checkIfNewTypeValid(NewTournamentTypeDto dto) {
    return tournamentTypeRepository.getTournamentTypeByTitle(dto.title()).isEmpty();
  }

  private boolean checkIfNewSeriesValid(NewTournamentSeriesDto dto) {
    // Probably, these checks should be done by the entity itself.
    if (dto.maxPlayers() < dto.minPlayers()) {
      return false;
    }
    if (dto.maxPlayers() < dto.table()) {
      return false;
    }
    if (!isGoodNumberForPlayersAtTable(dto.table())) {
      return false;
    }

    // Only this check is more or less should be in the service.
    return tournamentSeriesQueries.getTournamentSeriesByName(
        dto.uniqueName()
    ).isEmpty();
  }

  private boolean isGoodNumberForPlayersAtTable(int number) {
    return number == Table.SHORT.maxPlayers || number == Table.LONG.maxPlayers;
  }
}
