package com.example.chaos.core.queries;

import java.util.List;
import java.util.UUID;


public record GameMetaData(
    UUID id,
    String ownerHandle,
    String name,
    List<String> participants,
    boolean isDeleted,
    WorldInfo worldInfo
) {}
