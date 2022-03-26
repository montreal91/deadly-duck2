package com.example.chaos.core.commands;

import com.example.user.core.User;
import com.google.common.collect.ImmutableSet;


public record NewGameData(
    String name,
    User owner,
    ImmutableSet<User> participants,
    int maxSeasons
) {}
