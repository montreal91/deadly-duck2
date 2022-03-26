package com.example.chaos.core.queries;

import com.example.user.core.User;

import java.util.List;


public interface MetaGameQueries {
  List<GameMetaData> getGamesForOwner(String ownerHandle);
  List<GameMetaData> getGamesForParticipant(User participant);
}
