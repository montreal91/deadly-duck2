package com.example.pokker.webapi.datatransfer;

import com.example.pokker.core.TournamentSeries;

public record TournamentSeriesListView(
    String typeTitle,
    String uniqueName,
    int maxPlayersAtTable
) {
  public static TournamentSeriesListView fromTournamentSeries(
      TournamentSeries series
  ) {
    return new TournamentSeriesListView(
        series.getTypeTitle(), series.getTitle(), series.getMaxPlayersAtTable()
    );
  }
}
