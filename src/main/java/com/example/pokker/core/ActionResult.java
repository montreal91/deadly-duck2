package com.example.pokker.core;

public record ActionResult(boolean actionAccepted) {
  static ActionResult accepted() {
    return new ActionResult(true);
  }

  static ActionResult rejected() {
    return new ActionResult(false);
  }
}
