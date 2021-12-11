package com.example.tennis.core;


class WorldState {
  private final int maxSeasons;
  private int currentDay = 1;
  private int currentSeason = 1;

  WorldState(int maxSeasons) {
    this.maxSeasons = maxSeasons;
  }

  int getMaxSeasons() {
    return maxSeasons;
  }

  int getCurrentDay() {
    return currentDay;
  }

  int getCurrentSeason() {
    return currentSeason;
  }

  ActionResult acceptAction(Action action) {
    return new ActionResult(true);
  }

  private void update() {
    currentDay++;
  }

  private void goToNextSeason() {
    currentDay = 1;
    currentSeason++;
  }

  boolean isOver() {
    return currentSeason > maxSeasons;
  }
}
