package be.kdg.prog6.libraryBoundedContext.adapters.out.persistence;
import be.kdg.prog6.libraryBoundedContext.util.GameMapper;
import be.kdg.prog6.libraryBoundedContext.adapters.out.Entity.GameEntity;
import be.kdg.prog6.libraryBoundedContext.adapters.out.Entity.GameTypeEntity;
import be.kdg.prog6.libraryBoundedContext.domain.Game;
import be.kdg.prog6.libraryBoundedContext.domain.GameType;
import be.kdg.prog6.libraryBoundedContext.port.out.GameLoadPort;
import be.kdg.prog6.libraryBoundedContext.port.out.GameSavePort;
import be.kdg.prog6.common.events.util.NoAvailableGameException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GameDataBaseAdapter implements GameLoadPort, GameSavePort {

    private final GameJpaRepository gameJpaRepository;

    public GameDataBaseAdapter(GameJpaRepository gameJpaRepository) {
        this.gameJpaRepository = gameJpaRepository;
    }


    @Override
    public List<Game> fetchAvailableGames() {
        List<Game> gamesList = gameJpaRepository.findAll().stream()
                .map(GameMapper::mapToDomain)
                .toList();

        if (gamesList.isEmpty()) {
            throw new NoAvailableGameException("No available games found");
        }

        return gamesList;
    }

    @Override
    public Game fetchGameByName(String name) {
        return gameJpaRepository.findByGameName(name)
                .map(GameMapper::mapToDomain)
                .orElseThrow(() -> new IllegalArgumentException("Game not found with name: " + name));
    }

    @Override
    public List<Game> fetchGamesByCategory(GameType category) {
        List<Game> gamesList = gameJpaRepository.getGamesByCategory(GameTypeEntity.valueOf(category.toString())).stream()
                .map(GameMapper::mapToDomain)
                .toList();

        if (gamesList.isEmpty()) {
            throw new NoAvailableGameException("No available games found");
        }

        return gamesList;
    }

    @Override
    public void saveGame(Game game) {
        GameEntity gameEntity = GameMapper.mapToEntity(game);
        gameJpaRepository.save(gameEntity);
    }

//    @Override
//    public void saveListOfGames(List<Game> gamesList) {
//        List<GameEntity> entities = gamesList.stream()
//                .map(GameMapper::mapToEntity)
//                .toList();
//
//        gameJpaRepository.saveAll(entities);
//    }



}
