package com.example.pokker.core;

import com.example.pokker.core.datatransfer.NewTournamentSeriesDto;

import java.util.UUID;

public class TournamentSeries {
  final UUID id;
  final TournamentType tournamentType;
  final Table tables;
  final int minPlayers;
  final int maxPlayers;
  private String title;

  static TournamentSeries createNewTournamentSeries(
      NewTournamentSeriesDto validDto,
      TournamentType tournamentType
  ) {
    UUID id = UUID.randomUUID();
    return new TournamentSeries(
        id,
        tournamentType,
        Table.fromInt(validDto.table()),
        validDto.minPlayers(),
        validDto.maxPlayers(),
        validDto.uniqueName()
    );
  }

  TournamentSeries(
      UUID id,
      TournamentType tournamentType,
      Table tables,
      int minPlayers,
      int maxPlayers,
      String title
  ) {
    this.id = id;
    this.tournamentType = tournamentType;
    this.tables = tables;
    this.minPlayers = minPlayers;
    this.maxPlayers = maxPlayers;
    this.title = title;
  }

  void changeTitle(String newTitle) {
    title = newTitle;
  }

  public String getTitle() {
    return title;
  }

  public String getTypeTitle() {
    return tournamentType.getTitle();
  }

  public int getMaxPlayersAtTable() {
    return tables.maxPlayers;
  }
}
