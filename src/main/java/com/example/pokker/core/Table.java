package com.example.pokker.core;


public enum Table {
  // Probably, should not be public
  SHORT(6), LONG(10);
  public final int maxPlayers;

  Table(int maxPlayers) {
    this.maxPlayers = maxPlayers;
  }

  public static Table fromInt(int t) {
    if (t == 6) {
      return Table.SHORT;
    }
    if (t == 10) {
      return Table.LONG;
    }
    throw new RuntimeException("Bad table length value");
  }
}
