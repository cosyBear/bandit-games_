package be.kdg.prog6.storeBoundedContext.adapters.out;

import be.kdg.prog6.common.exception.EntityNotFoundException;
import be.kdg.prog6.storeBoundedContext.adapters.out.Entity.GameEntity;
import be.kdg.prog6.storeBoundedContext.adapters.out.persistence.GameJpaRepository;
import be.kdg.prog6.storeBoundedContext.domain.Game;
import be.kdg.prog6.storeBoundedContext.domain.Store;
import be.kdg.prog6.storeBoundedContext.domain.id.GameId;
import be.kdg.prog6.storeBoundedContext.port.out.GameLoadPort;
import be.kdg.prog6.storeBoundedContext.port.out.StoreSavePort;
import be.kdg.prog6.storeBoundedContext.util.CustomMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Slf4j
public class GameDatabaseAdapter implements GameLoadPort, StoreSavePort {


    //TODO impelent a way so you cant save the same game two times

    private final GameJpaRepository gameJpaRepository;


    @Override
    public Store loadAllAvailGames() {

        Set<Game> games = gameJpaRepository.getAllGame()
                .stream()
                .map(CustomMapper::toGame)
                .collect(Collectors.toSet());

        return new Store(games);
    }

    @Override
    public Game loadGameById(GameId gameId) {
        GameEntity gameEntity = gameJpaRepository.findGameByGameId(gameId.id());

        if (gameEntity == null) {
            throw new EntityNotFoundException(gameId.id().toString());
        }
        return CustomMapper.toGame(gameEntity);
    }

    @Override
    public void save(Store store) {
        Set<GameEntity> updatedGames = store.getAvailableGames()
                .stream()
                .map(CustomMapper::toGameEntity)
                .collect(Collectors.toSet());

        Set<GameEntity> existingGames = new HashSet<>(gameJpaRepository.findAll());

        existingGames.stream()
                .filter(gameEntity -> !updatedGames.contains(gameEntity))
                .forEach(gameJpaRepository::delete);

        gameJpaRepository.saveAll(updatedGames);
    }


}
