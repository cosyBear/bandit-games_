package be.kdg.prog6.statistics.domain;

import java.time.LocalDateTime;

public class ChatbotResponse {
    private String userQuery;
    private String response;
    private String context;
    private LocalDateTime timestamp;

    public ChatbotResponse(String userQuery, String response, String context, LocalDateTime timestamp) {
        this.userQuery = userQuery;
        this.response = response;
        this.context = context;
        this.timestamp = timestamp;
    }

    public String getUserQuery() {
        return userQuery;
    }

    public void setUserQuery(String userQuery) {
        this.userQuery = userQuery;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isValidResponse() {
        return response != null && !response.isEmpty();
    }
}
