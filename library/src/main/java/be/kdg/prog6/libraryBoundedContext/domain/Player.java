package be.kdg.prog6.libraryBoundedContext.domain;

import be.kdg.prog6.libraryBoundedContext.domain.id.PlayerId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Data @AllArgsConstructor @NoArgsConstructor
public class Player {

    private PlayerId playerId;

}
//TODO remove the player object from the Library bounded Context and place PlayerId in teh Library instead of player object