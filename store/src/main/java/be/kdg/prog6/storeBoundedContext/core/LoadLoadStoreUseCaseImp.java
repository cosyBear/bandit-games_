package be.kdg.prog6.storeBoundedContext.core;

import be.kdg.prog6.storeBoundedContext.domain.Store;
import be.kdg.prog6.storeBoundedContext.domain.id.GameId;
import be.kdg.prog6.storeBoundedContext.port.in.gameQuery.GameQuery;
import be.kdg.prog6.storeBoundedContext.port.in.store.LoadStoreUseCase;
import be.kdg.prog6.storeBoundedContext.port.out.GameLoadPort;
import be.kdg.prog6.storeBoundedContext.util.CustomMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class LoadLoadStoreUseCaseImp implements LoadStoreUseCase {


    private final GameLoadPort gameLoadPort;


    @Override
    @Transactional
    public List<GameQuery> viewAllGames() {

        Store store = gameLoadPort.loadAllAvailGames();

        List<GameQuery> gameQueries = store.getAvailableGames().stream()
                .map(CustomMapper::toGameQuery)
                .toList();

        log.info("Retrieved {} games from the database", gameQueries.size());

        return gameQueries;
    }

    @Override
    public GameQuery viewGameDetails(GameId gameId) {
        return CustomMapper.toGameQuery(gameLoadPort.loadGameById(gameId));
    }


}
