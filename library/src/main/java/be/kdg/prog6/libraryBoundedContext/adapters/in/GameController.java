package be.kdg.prog6.libraryBoundedContext.adapters.in;

import be.kdg.prog6.libraryBoundedContext.domain.id.PlayerId;
import be.kdg.prog6.libraryBoundedContext.port.in.game.GameQueryUseCase;
import be.kdg.prog6.libraryBoundedContext.port.in.game.GameUseCase;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.FetchGamesByNameQuery;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.GameQuery;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.GetGamesByCategoryQuery;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.RetrieveAllGamesQuery;
import be.kdg.prog6.libraryBoundedContext.domain.GameType;
import be.kdg.prog6.libraryBoundedContext.port.in.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "http://localhost:5173")
public class GameController {

    private final GameQueryUseCase gameQueryUseCase;
    private final GameUseCase gameUseCase;

    public GameController(GameQueryUseCase gameQueryUseCase, GameUseCase gameUseCase) {
        this.gameQueryUseCase = gameQueryUseCase;
        this.gameUseCase = gameUseCase;
    }


    @GetMapping("/{playerId}")
    public ResponseEntity<List<GameQuery>> fetchAllAvailableGames(@PathVariable UUID playerId) {
        RetrieveAllGamesQuery query = new RetrieveAllGamesQuery(new PlayerId(playerId));
        return ResponseEntity.status(HttpStatus.OK).body(gameQueryUseCase.getAllAvailableGame(query));
    }

    @GetMapping("/search")
    public ResponseEntity<List<GameQuery>> fetchGamesByName(
            @RequestParam UUID playerId,
            @RequestParam String gameName) {

        FetchGamesByNameQuery query = new FetchGamesByNameQuery(new PlayerId(playerId), gameName);

        List<GameQuery> results = gameQueryUseCase.fetchGamesByName(query);

        return ResponseEntity.status(HttpStatus.OK).body(results);

    }


    @GetMapping("category")
    public ResponseEntity<List<GameQuery>> fetchGamesByCategory(
            @RequestParam UUID playerId,
            @RequestParam String category) {

        GetGamesByCategoryQuery query = new GetGamesByCategoryQuery(new PlayerId(playerId), GameType.valueOf(category));

        List<GameQuery> results = gameQueryUseCase.getGamesByCategory(query);

        return ResponseEntity.status(HttpStatus.OK).body(results);

    }


    @PatchMapping("/{playerId}/{gameId}/favorite")
    public ResponseEntity<GameQuery> makeGameAsFavorite(
            @PathVariable UUID playerId,
            @PathVariable UUID gameId,
            @RequestParam String gameName) {

        GameCommand command = new GameCommand(new PlayerId(playerId), gameName, gameId);

        GameQuery updatedGame = gameUseCase.markGameAsFavourite(command);

        return ResponseEntity.status(HttpStatus.OK).body(updatedGame);
    }



    @PostMapping("/{playerId}/games/{gameId}/achievements")
    public ResponseEntity<GameQuery> earnAchievement(
            @PathVariable UUID playerId,
            @PathVariable UUID gameId,
            @RequestParam String gameName,
            @RequestParam String achievementName) {

        EarnAchievementCommand command = new EarnAchievementCommand(
                new PlayerId(playerId),
                gameName,
                gameId,
                achievementName
        );

        GameQuery updatedGame = gameUseCase.givePlayerAnAchievement(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(updatedGame);
    }


}
