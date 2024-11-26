package be.kdg.prog6.friends;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FriendsBondedContextApplication {

    public static void main(String[] args) {
        SpringApplication.run(FriendsBondedContextApplication.class, args);
    }

}



// so a player click player i create a lobby
// that will cause an event to send to the firends bounded context
// than the i save that event in the table
// the player in the front end will see list of the evetns aka lobbys that is open that is for his firends only
// now after the player click accpet will send a command to the lobby bounded context to join the lobbby and that will cause an event back to the firends bounde context
// to change the status of the lobby to full just so that two players can join the lobby.

