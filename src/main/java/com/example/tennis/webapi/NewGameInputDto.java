package com.example.tennis.webapi;

import java.util.Set;

public record NewGameInputDto(
    String name,
    String ownerHandle,
    Set<String> participants,
    int maxSeasons
) {}
