package com.example.pokker.core;

import java.util.List;

public record Reward(int rating, List<Ticket> tickets) {}
