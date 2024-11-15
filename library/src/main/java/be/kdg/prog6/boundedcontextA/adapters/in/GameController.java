package be.kdg.prog6.boundedcontextA.adapters.in;

import be.kdg.prog6.boundedcontextA.GameMapper;
import be.kdg.prog6.boundedcontextA.adapters.in.dto.CreateGameDto;
import be.kdg.prog6.boundedcontextA.domain.GameType;
import be.kdg.prog6.boundedcontextA.port.in.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameQueryUseCase gameQueryUseCase;
    private final GameUseCase gameUseCase;

    public GameController(GameQueryUseCase gameQueryUseCase, GameUseCase gameUseCase) {
        this.gameQueryUseCase = gameQueryUseCase;
        this.gameUseCase = gameUseCase;
    }


    @GetMapping
    public ResponseEntity<List<GameQuery>> fetchAllAvailableGames() {
        return ResponseEntity.status(HttpStatus.OK).body(gameQueryUseCase.getAllAvailableGame());
    }

    @GetMapping("/{gameName}")
    public ResponseEntity<GameQuery> fetchGameByName(@PathVariable String gameName) {
        return ResponseEntity.status(HttpStatus.OK).body(gameQueryUseCase.getGameByName(gameName));
    }


    @GetMapping("/search")
    public ResponseEntity<List<GameQuery>> fetchGamesByCategory(@RequestParam String category) {
        return ResponseEntity.status(HttpStatus.OK).body(gameQueryUseCase.getGamesByCategory(GameType.valueOf(category)));
    }

    @PatchMapping("/{gameName}/favorite")
    public ResponseEntity<GameQuery> makeGameAsFavorite(@PathVariable String gameName) {
        GameQuery updatedGame = gameUseCase.markGameAsFavourite(new GameCommand(gameName));
        return ResponseEntity.ok(updatedGame);
    }

    @PostMapping
    public ResponseEntity<GameQuery> addGame(@RequestBody CreateGameDto game) {
        CreateGameCommand command = GameMapper.toCommand(game);
        GameQuery createdGame = gameUseCase.createGame(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);

    }



}
