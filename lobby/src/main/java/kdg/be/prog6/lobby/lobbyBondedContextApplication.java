package kdg.be.prog6.lobby;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class lobbyBondedContextApplication {

    public static void main(String[] args) {
        SpringApplication.run(lobbyBondedContextApplication.class, args);
    }

}
