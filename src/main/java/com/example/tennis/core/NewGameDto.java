package com.example.tennis.core;

import com.example.user.core.User;
import com.google.common.collect.ImmutableSet;


public record NewGameDto(
    String name,
    User owner,
    ImmutableSet<User> participants,
    int maxSeasons
) {}
