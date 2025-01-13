package be.kdg.prog6.storeBoundedContext.adapters.out.web;

import be.kdg.prog6.storeBoundedContext.domain.PurchaseRequestCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "game-service",
        url = "http://localhost:8090/api/games",
        configuration = GameWebClientConfig.class
)
public interface GamesWebClient {

    @PostMapping("/ownership")
    Boolean checkOwnership(@RequestBody PurchaseRequestCommand request);
}
