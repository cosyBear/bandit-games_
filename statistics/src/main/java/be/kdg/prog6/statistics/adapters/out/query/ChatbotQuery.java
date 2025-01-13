package be.kdg.prog6.statistics.adapters.out.query;


import com.fasterxml.jackson.annotation.JsonProperty;

public record ChatbotQuery(
        @JsonProperty("user_input")
        String userInput,
        @JsonProperty("context")
        String context
) {}

