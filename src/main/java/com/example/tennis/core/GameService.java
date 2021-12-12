package com.example.tennis.core;

import com.example.user.core.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


public class GameService {
  private final Map<UUID, Game> activeGames = new HashMap<>();
  private final GameRepository gameRepository;

  public GameService(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  public void createGame(NewGameDto dto) {
    Game game = Game.makeGameFromCreateGameDto(dto);
    gameRepository.saveGame(game);
  }

  public void loadGame(UUID gameUuid) {
    Optional<Game> game = gameRepository.getGameByUuid(gameUuid);
    game.ifPresent(value -> activeGames.put(value.metadata.getId(), value));
  }

  public void exitGame(UUID gameUid) {
    Optional<Game> possiblyGame = Optional.ofNullable(activeGames.get(gameUid));
    possiblyGame.ifPresent(gameRepository::saveGame);
  }
  public void deleteGame(UUID gameId) {
    Optional<Game> game = gameRepository.getGameByUuid(gameId);
    game.ifPresent(value -> {
      activeGames.remove(gameId);
      value.metadata.delete();
      gameRepository.saveGame(value);
    });
  }

  public ActionResult makeAction(Action action) {
    if (!activeGames.containsKey(action.gameUuid)) {
      return new ActionResult(false);
    }
    return activeGames.get(action.gameUuid).acceptAction(action);
  }

  public List<Game> getGamesForOwner(String ownerHandle) {
    return gameRepository.getAllGamesByOwner(ownerHandle);
  }

  public List<Game> getGamesForParticipant(User participant) {
    return Collections.emptyList();
  }
}
