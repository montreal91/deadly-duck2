package com.example.chaos.core;

import com.example.chaos.core.commands.MetaGameCommands;
import com.example.chaos.core.commands.NewGameData;
import com.example.chaos.core.queries.GameMetaData;
import com.example.chaos.core.queries.MetaGameQueries;
import com.example.user.core.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


public class GameService implements MetaGameCommands, MetaGameQueries {
  private final Map<UUID, Game> activeGames = new HashMap<>();
  private final GameRepository gameRepository;

  public GameService(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  @Override
  public void createGame(NewGameData dto) {
    Game game = Game.makeGameFromCreateGameDto(dto);
    gameRepository.saveGame(game);
  }

  @Override
  public void loadGame(UUID gameUuid) {
    Optional<Game> game = gameRepository.getGameByUuid(gameUuid);
    game.ifPresent(value -> activeGames.put(value.metadata.getId(), value));
  }

  @Override
  public void exitGame(UUID gameUid) {
    Optional<Game> possiblyGame = Optional.ofNullable(activeGames.get(gameUid));
    possiblyGame.ifPresent(gameRepository::saveGame);
  }

  @Override
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

  @Override
  public List<GameMetaData> getGamesForOwner(String ownerHandle) {
    return gameRepository.getAllGamesByOwner(ownerHandle)
                         .stream()
                         .map(GameService::gameToData)
                         .toList();
  }

  @Override
  public List<GameMetaData> getGamesForParticipant(User participant) {
    return Collections.emptyList();
  }

  private static GameMetaData gameToData(Game game) {
    Metadata md = game.getMetadata();
    List<String> participants = md.getParticipants()
                                  .stream()
                                  .map(User::getHandle)
                                  .toList();
    return new GameMetaData(
        md.getId(),
        md.getOwner().getHandle(),
        md.getName(),
        participants,
        md.isDeleted(),
        game.getWorldInfo()
    );
  }
}
