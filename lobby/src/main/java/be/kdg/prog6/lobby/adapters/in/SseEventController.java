package be.kdg.prog6.lobby.adapters.in;


import be.kdg.prog6.lobby.port.out.LobbySsePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class SseEventController {


    private final LobbySsePort lobbySsePort;


    @GetMapping("/events")
    public SseEmitter subscribeToEvents() {
        return lobbySsePort.createEmitter();
    }


}
