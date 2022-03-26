package com.example.chaos.infrastructure;

import com.example.chaos.core.Game;
import com.example.chaos.core.GameRepository;
import com.example.chaos.core.Metadata;
import com.google.common.collect.ImmutableList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


public class GameRepositoryPrototype implements GameRepository {
  private final Map<String, Map<String, Game>> storage = new HashMap<>();

  @Override
  public void saveGame(Game game) {
    Metadata gameMetadata = game.getMetadata();
    String ownerHandle = gameMetadata.getOwner().getHandle();

    if (!storage.containsKey(ownerHandle)) {
      storage.put(ownerHandle, new HashMap<>());
    }

    storage.get(ownerHandle).put(gameMetadata.getName(), game);
  }

  @Override
  public Optional<Game> getGameByUuid(UUID gameUuid) {
    return Optional.empty();
  }

  @Override
  public List<Game> getAllGamesByOwner(String userHandle) {
    if (storage.containsKey(userHandle)) {
      return ImmutableList.copyOf(storage.get(userHandle).values());
    }

    return Collections.emptyList();
  }

  @Override
  public Optional<Game> getGameByOwnerAndHandle(
      String ownerHandle, String gameName
  ) {
    if (storage.containsKey(ownerHandle)) {
      return Optional.ofNullable(storage.get(ownerHandle).get(gameName));
    }

    return Optional.empty();
  }
}
