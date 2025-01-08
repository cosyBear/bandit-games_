package be.kdg.prog6.lobby.adapters.out;

import be.kdg.prog6.common.events.LobbyRequestEvent;
import be.kdg.prog6.common.events.StartGameEvent;
import be.kdg.prog6.lobby.port.in.StartGame;
import be.kdg.prog6.lobby.port.out.StartGameSsePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Component
@Slf4j
@RequiredArgsConstructor

public class StartGameSseAdapter implements StartGameSsePort {

    private AtomicReference<SseEmitter> reference = new AtomicReference<>();

    @Override
    public SseEmitter createEmitter() {
        SseEmitter emitter = new SseEmitter(0L);
        reference.set(emitter);

        emitter.onCompletion(() -> {
            log.info("Game connection completed.");
            reference.set(null);
        });

        emitter.onTimeout(() -> {
            log.warn("Game connection timed out.");
            reference.set(null);
        });

        emitter.onError((ex) -> {
            log.error("Error occurred in game SSE: {}", ex.getMessage());
            reference.set(null);
        });

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            try {
                emitter.send(SseEmitter.event().data("keep-alive"));
            } catch (IOException e) {
                log.warn("Game keep-alive failed: {}", e.getMessage());
            }
        }, 0, 30, TimeUnit.SECONDS);

        return emitter;
    }

    @Override
    public void sendEvent(StartGameEvent event) {
        SseEmitter emitter = reference.get();
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().data(event));
                log.info("Game event sent: {}", event.toString());

            } catch (IOException e) {
                log.error("Failed to send game event: {}", e.getMessage());
                reference.set(null);
            }
        } else {
            log.warn("No active game SSE connection to send events");
        }
    }
}