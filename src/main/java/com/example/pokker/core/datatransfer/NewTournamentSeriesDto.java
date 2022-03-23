package com.example.pokker.core.datatransfer;

import java.util.UUID;


public record NewTournamentSeriesDto(
    UUID tournamentTypeId,
    int table,
    int minPlayers,
    int maxPlayers,
    String uniqueName
) {}
