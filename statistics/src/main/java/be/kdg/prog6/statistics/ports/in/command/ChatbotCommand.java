package be.kdg.prog6.statistics.ports.in.command;

import java.util.UUID;

public record ChatbotCommand(UUID userId, String userInput, String context) {}
