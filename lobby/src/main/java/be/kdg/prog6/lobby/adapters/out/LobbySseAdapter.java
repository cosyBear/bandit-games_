package be.kdg.prog6.lobby.adapters.out;

import be.kdg.prog6.common.events.LobbyRequestEvent;
import be.kdg.prog6.lobby.port.out.LobbySsePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.yaml.snakeyaml.emitter.Emitter;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Component
@Slf4j
@RequiredArgsConstructor
public class LobbySseAdapter implements LobbySsePort {

    private AtomicReference<SseEmitter> reference = new AtomicReference<>();


    @Override
    public SseEmitter createEmitter() {
        SseEmitter emitter = new SseEmitter(0L);
        reference.set(emitter);

        emitter.onCompletion(() -> {
            log.info("Connection completed.");
            reference.set(null);
        });

        emitter.onTimeout(() -> {
            log.warn("Connection timed out.");
            reference.set(null);
        });

        emitter.onError((ex) -> {
            log.error("Error occurred: {}", ex.getMessage());
            reference.set(null);
        });


        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            try {
                emitter.send(SseEmitter.event().data("keep-alive"));
            } catch (IOException e) {
                log.warn("Keep-alive failed: {}", e.getMessage());
            }
        }, 0, 30, TimeUnit.SECONDS);// keep connection alive maybe overkill but needed here


        return emitter;

    }

    @Override
    public void sendEvent(LobbyRequestEvent event) {
        SseEmitter emitter = reference.get();
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().data(event));
                log.info("Event sent: {}", event.toString());

            } catch (IOException e) {
                log.error("Failed to send event: {}", e.getMessage());
                reference.set(null);
            }
        } else
            log.warn("No active SSE connection to send events");


    }
}
