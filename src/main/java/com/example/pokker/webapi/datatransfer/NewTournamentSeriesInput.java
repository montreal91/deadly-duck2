package com.example.pokker.webapi.datatransfer;

public record NewTournamentSeriesInput(
    String tournamentTypeId,
    int table,
    int minPlayers,
    int maxPlayers,
    String uniqueName
) {}
