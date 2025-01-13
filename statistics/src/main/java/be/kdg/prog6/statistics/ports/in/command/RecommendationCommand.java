package be.kdg.prog6.statistics.ports.in.command;

import be.kdg.prog6.statistics.domain.RecommendationType;

public record RecommendationCommand(RecommendationType recommendationType, String id) {}
