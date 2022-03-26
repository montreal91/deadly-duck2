package com.example.chaos.core;

import com.example.chaos.core.commands.Action;
import com.example.chaos.core.commands.ActionResult;
import com.example.chaos.core.commands.GameInfo;
import com.example.chaos.core.commands.GameManagementCommands;
import com.example.chaos.core.commands.NewGameData;
import com.example.chaos.core.queries.GameMetaData;
import com.example.chaos.core.queries.MetaGameQueries;
import com.example.user.core.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public final class GameService
    implements GameManagementCommands, MetaGameQueries {
//  private final Map<UUID, Game> activeGames = new HashMap<>();
  private final GameRepository gameRepository;
  private final GameServer server;

  public GameService(GameRepository gameRepository, GameServer server) {
    this.gameRepository = gameRepository;
    this.server = server;
  }

  @Override
  public void createGame(NewGameData dto) {
    Game game = Game.makeGameFromCreateGameDto(dto);
    gameRepository.saveGame(game);
  }

  @Override
  public void loadGame(GameInfo gameInfo) {
    Optional<Game> game = gameRepository.getGameByUuid(gameInfo.id());
    game.ifPresent(server::addGame);
  }

  @Override
  public void exitGame(GameInfo gameInfo) {
    Optional<Game> possiblyGame = server.remove(gameInfo);
    possiblyGame.ifPresent(gameRepository::saveGame);
  }

  @Override
  public void deleteGame(GameInfo gameInfo) {
//    Optional<Game> game = gameRepository.getGameByUuid(gameInfo.id());
//    game.ifPresent(value -> {
//      activeGames.remove(gameInfo);
//      value.metadata.delete();
//      gameRepository.saveGame(value);
//    });
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
