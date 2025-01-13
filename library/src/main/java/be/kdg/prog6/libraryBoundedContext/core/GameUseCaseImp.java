package be.kdg.prog6.libraryBoundedContext.core;

import be.kdg.prog6.common.events.util.LibraryNotFoundException;
import be.kdg.prog6.libraryBoundedContext.domain.Library;
import be.kdg.prog6.libraryBoundedContext.domain.id.GameId;
import be.kdg.prog6.libraryBoundedContext.domain.id.PlayerId;
import be.kdg.prog6.libraryBoundedContext.port.in.command.AddGameCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.command.EarnAchievementCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.command.GameCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.command.PlayerGameOwnershipCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.game.GameUseCase;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.GameQuery;
import be.kdg.prog6.libraryBoundedContext.port.out.LibraryLoadPort;
import be.kdg.prog6.libraryBoundedContext.port.out.LibrarySavePort;
import be.kdg.prog6.libraryBoundedContext.util.Mapper;
import be.kdg.prog6.libraryBoundedContext.domain.Game;
import be.kdg.prog6.common.events.util.GameNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class GameUseCaseImp implements GameUseCase {

    private final LibraryLoadPort libraryLoadPort;
    private final LibrarySavePort librarySavePort;

    @Transactional
    @Override
    public GameQuery toggleGameFavourite(GameCommand command) {

        Library library = libraryLoadPort.fetchLibraryWithGameById(command.gameId());
        if (library == null) {
            log.error("Library not found or game not found for player: {}", command.playerId());
            throw new GameNotFoundException("Game not found with id: " + command.gameId());
        }

        Game game = library.toggleFavouriteForGame(new GameId(command.gameId()));

        librarySavePort.save(library);
        log.info("Game marked as favorite: ");

        return Mapper.toQuery(game);
    }


    @Transactional
    @Override
    public GameQuery givePlayerAnAchievement(EarnAchievementCommand command) {
        Library library = libraryLoadPort.fetchLibraryWithGamesByNamePattern(command.playerId(), command.gameName());
        if (library == null) {
            log.error("Library not found for player: {}", command.playerId());
            throw new LibraryNotFoundException("Library not found for player ID: {}" + command.playerId());
        }

        Game game = library.givePlayerAnAchievement(command.gameName(), command.AchievementName());

        librarySavePort.save(library);
        return Mapper.toQuery(game);
    }

    @Override
    @Transactional
    public Boolean hasPlayerPurchasedGame(PlayerGameOwnershipCommand command) {

        PlayerId playerId = command.playerId();
        Library library = libraryLoadPort.fetchLibraryWithAllAvailableGames(playerId);


        return library.containsGame(command.gameName());
    }

    @Override
    @Transactional
    public void addGameToPlayerLibrary(AddGameCommand command) {

        Library library = libraryLoadPort.fetchLibraryWithAllAvailableGames(command.playerId());

        List<Game> mutableGames = new ArrayList<>(library.getGames());
        mutableGames.add(command.game());

        library.setGames(mutableGames);


        librarySavePort.save(library);
        log.info("games has been added and saved to the player  library: {}", library);


    }


}
