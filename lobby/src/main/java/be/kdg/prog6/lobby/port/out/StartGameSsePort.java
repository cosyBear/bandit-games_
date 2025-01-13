package be.kdg.prog6.lobby.port.out;

import be.kdg.prog6.common.events.LobbyRequestEvent;
import be.kdg.prog6.common.events.StartGameEvent;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface StartGameSsePort {
    SseEmitter createEmitter();
    void sendEvent(StartGameEvent event);
}
