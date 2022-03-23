package com.example.pokker.webapi.datatransfer;

import com.example.pokker.core.TournamentType;

public record TournamentTypeDetailsView(String tournamentTypeId, String title) {
  public static TournamentTypeDetailsView fromTournamentType(TournamentType type) {
    return new TournamentTypeDetailsView(
        type.getId().toString(), type.getTitle()
    );
  }
}
