package be.kdg.prog6.libraryBoundedContext.adapters.in;

import be.kdg.prog6.libraryBoundedContext.adapters.in.dto.ExportDataDto;
import be.kdg.prog6.libraryBoundedContext.adapters.in.dto.PlayerGameOwnershipCommandDto;
import be.kdg.prog6.libraryBoundedContext.domain.id.PlayerId;
import be.kdg.prog6.libraryBoundedContext.port.in.command.ExportUserDataCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.command.GameCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.command.PlayerGameOwnershipCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.game.ExportUserData;
import be.kdg.prog6.libraryBoundedContext.port.in.game.GameQueryUseCase;
import be.kdg.prog6.libraryBoundedContext.port.in.game.GameUseCase;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.FetchGamesByNameQuery;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.GameQuery;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.GetGamesByCategoryQuery;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.RetrieveAllGamesQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
@Slf4j
public class GameController {

    private final GameQueryUseCase gameQueryUseCase;
    private final GameUseCase gameUseCase;
    private final ExportUserData exportUserData;


    @GetMapping("/userData")
    @PreAuthorize("hasAuthority('GameAndEvents')")
    public ResponseEntity<byte[]> exportLibrary(@RequestParam UUID playerId , @RequestParam String playerName) {
        byte[] fileContent = exportUserData.exportUserData(new ExportUserDataCommand(new PlayerId(playerId),playerName));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"library_export.csv\"");
        headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");

        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
    }


    @GetMapping("/{playerId}")
    @PreAuthorize("hasAuthority('GameAndEvents')")
    public ResponseEntity<List<GameQuery>> fetchAllAvailableGames(@PathVariable UUID playerId) {
        RetrieveAllGamesQuery query = new RetrieveAllGamesQuery(new PlayerId(playerId));
        return ResponseEntity.status(HttpStatus.OK).body(gameQueryUseCase.getAllAvailableGame(query));
    }

    @GetMapping("/search")
    @PreAuthorize("hasAuthority('GameAndEvents')")
    public ResponseEntity<List<GameQuery>> fetchGamesByName(
            @RequestParam("player") UUID playerId,
            @RequestParam("game") String gameName) {

        FetchGamesByNameQuery query = new FetchGamesByNameQuery(new PlayerId(playerId), gameName);

        List<GameQuery> results = gameQueryUseCase.fetchGamesByName(query);

        return ResponseEntity.status(HttpStatus.OK).body(results);

    }

    @GetMapping("/details/{gameId}")
    @PreAuthorize("hasAuthority('GameAndEvents')")
    public ResponseEntity<GameQuery> fetchGameDetails(@PathVariable UUID gameId) {
        final GameQuery gameQuery = gameQueryUseCase.findGameById(gameId);

        return ResponseEntity.status(HttpStatus.OK).body(gameQuery);
    }


    @GetMapping("category")
    @PreAuthorize("hasAuthority('GameAndEvents')")
    public ResponseEntity<List<GameQuery>> fetchGamesByCategory(
            @RequestParam("player") UUID playerId,
            @RequestParam("category") String category) {

        GetGamesByCategoryQuery query = new GetGamesByCategoryQuery(new PlayerId(playerId), category);

        List<GameQuery> results = gameQueryUseCase.getGamesByCategory(query);

        return ResponseEntity.status(HttpStatus.OK).body(results);

    }


    @PatchMapping("/{playerId}/{gameId}/favorite")
    @PreAuthorize("hasAuthority('GameAndEvents')")
    public ResponseEntity<GameQuery> toggleGameFavorite(
            @PathVariable UUID playerId,
            @PathVariable UUID gameId) {

        GameCommand command = new GameCommand(new PlayerId(playerId), gameId);

        GameQuery updatedGame = gameUseCase.toggleGameFavourite(command);

        return ResponseEntity.status(HttpStatus.OK).body(updatedGame);
    }


    @PostMapping("/ownership")
    @PreAuthorize("hasAuthority('GameAndEvents')")
    public ResponseEntity<Boolean> PlayerOwnGame(@RequestBody PlayerGameOwnershipCommandDto dto) {
        final PlayerGameOwnershipCommand command = new PlayerGameOwnershipCommand(new PlayerId(dto.playerId()), dto.gameName());

        return ResponseEntity.status(HttpStatus.OK).body(gameUseCase.hasPlayerPurchasedGame(command));

    }




}
