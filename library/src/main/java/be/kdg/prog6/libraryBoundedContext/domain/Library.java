package be.kdg.prog6.libraryBoundedContext.domain;

import be.kdg.prog6.libraryBoundedContext.domain.id.GameId;
import be.kdg.prog6.libraryBoundedContext.domain.id.LibraryId;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Library {

    private LibraryId libraryId;
    private List<Game> games ;


    public Library(){
        this.games = new ArrayList<Game>();
    }

    public Library(LibraryId libraryId , List<Game> games){
        this.libraryId = libraryId;
        this.games = games;
    }


    public String addGame(Game game){
        games.add(game);
        return "game is added ";
    }

    public String removeGame(Game game){
        games.remove(game);
        return "game is removed ";
    }

    private void markGameAsFavorite(String gameName){
        games.stream().filter(
                game -> game.getGameName().equals(gameName)
        ).findFirst().ifPresent(
                Game::markAsFavorite
        );

    }

    private String givePlayerAnAchievement(GameId gameId, Achievement achievement) {
        return games.stream()
                .filter(game -> game.getGameId().equals(gameId))
                .findFirst()
                .map(game -> game.givePlayerAnAchievement(achievement))
                .orElse("Game not found with the given GameId.");
    }






}
