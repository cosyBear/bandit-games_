package be.kdg.prog6.storeBoundedContext.adapters.in;


import be.kdg.prog6.storeBoundedContext.domain.id.GameId;
import be.kdg.prog6.storeBoundedContext.port.in.command.CreateGameCommand;
import be.kdg.prog6.storeBoundedContext.port.in.command.RemoveGameCommand;
import be.kdg.prog6.storeBoundedContext.port.in.gameQuery.GameQuery;
import be.kdg.prog6.storeBoundedContext.port.in.store.LoadStoreUseCase;
import be.kdg.prog6.storeBoundedContext.port.in.store.StoreUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:5173")
public class StoreController {

    private final LoadStoreUseCase loadStoreUseCase;
    private final StoreUseCase storeUseCase;

    @GetMapping
    public ResponseEntity<List<GameQuery>> getAllGame() {
        return ResponseEntity.status(HttpStatus.OK).body(loadStoreUseCase.viewAllGames());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('dev')")
    public ResponseEntity<String> addGame(@RequestBody CreateGameCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(storeUseCase.addGameToStore(command));
    }

    @DeleteMapping("/game/{id}")
    @PreAuthorize("hasAuthority('dev')")
    public ResponseEntity<String> deleteGame(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(storeUseCase.removeGameFromStore(new RemoveGameCommand(id)));
    }

    @GetMapping("/{gameId}/details")
    public ResponseEntity<GameQuery> getGameDetails(@PathVariable("gameId") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(loadStoreUseCase.viewGameDetails(new GameId(id)));
    }

}