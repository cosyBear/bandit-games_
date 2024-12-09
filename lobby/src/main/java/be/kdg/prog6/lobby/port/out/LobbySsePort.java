package be.kdg.prog6.lobby.port.out;

import be.kdg.prog6.common.events.LobbyRequestEvent;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface LobbySsePort {

    SseEmitter createEmitter();
    void sendEvent(LobbyRequestEvent event);

}
