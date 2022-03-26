package com.example.chaos.core.commands;

import com.google.common.collect.ImmutableSet;


public record NewGameData(
    String name,
    UserInfo owner,
    ImmutableSet<UserInfo> participants
) {}
