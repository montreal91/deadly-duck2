package com.example.pokker.core;

import java.time.LocalDateTime;
import java.util.UUID;

public record Results(
    int position,
    UUID user,
    LocalDateTime timestamp,
    Combination combination
) {}
