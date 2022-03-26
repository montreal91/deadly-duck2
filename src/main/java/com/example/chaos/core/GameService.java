package com.example.chaos.core;

import com.example.chaos.core.commands.GameInfo;
import com.example.chaos.core.commands.GameManagementCommands;
import com.example.chaos.core.commands.NewGameData;
import com.example.chaos.core.commands.UserInfo;
import com.example.chaos.core.queries.GameMetaData;
import com.example.chaos.core.queries.MetaGameQueries;
import com.example.user.core.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


public final class GameService
    implements GameManagementCommands, MetaGameQueries {
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
  public void loadGame(UserInfo userInfo, GameInfo gameInfo) {
    Optional<Game> game = gameRepository.getGameByUuid(gameInfo.id());
    game.ifPresent(gameObj -> {
      if (gameObj.isOwnedBy(userInfo)) {
        server.addGame(gameObj);
      }
    });
  }

  @Override
  public void exitGame(GameInfo gameInfo) {
    Optional<Game> possiblyGame = server.remove(gameInfo);
    possiblyGame.ifPresent(gameRepository::saveGame);
  }

  @Override
  public void startGame(UserInfo userInfo, GameInfo gameInfo) {
    Optional<Game> game = server.getGame(gameInfo);
    game.ifPresent(gameObj -> {
      if (gameObj.isOwnedBy(userInfo)) {
        server.runGame(gameInfo);
      }
    });
  }

  @Override
  public void deleteGame(GameInfo gameInfo) {
    Optional<Game> activeGame = server.remove(gameInfo);
    activeGame.ifPresent(this::deleteGame);

    if (activeGame.isPresent()) {
      return;
    }

    Optional<Game> game = gameRepository.getGameByUuid(gameInfo.id());
    game.ifPresent(this::deleteGame);
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

  private void deleteGame(Game game) {
    game.metadata.delete();
    gameRepository.saveGame(game);
  }

  private static GameMetaData gameToData(Game game) {
    Metadata md = game.getMetadata();
    List<String> participants = md.getParticipants()
                                  .stream()
                                  .map(UserInfo::name)
                                  .toList();
    return new GameMetaData(
        md.getId(),
        md.getOwner().name(),
        md.getName(),
        participants,
        md.isDeleted(),
        game.getWorldInfo()
    );
  }
}
