package be.kdg.prog6.statistics.adapters.in;

public class ChatbotRequest {
    private String userInput;
    private String context;

    public ChatbotRequest() {}

    public ChatbotRequest(String userInput, String context) {
        this.userInput = userInput;
        this.context = context;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
