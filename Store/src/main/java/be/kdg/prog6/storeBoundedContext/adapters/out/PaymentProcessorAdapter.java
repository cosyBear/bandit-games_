package be.kdg.prog6.storeBoundedContext.adapters.out;

import be.kdg.prog6.common.exception.GameAlreadyOwnedException;
import be.kdg.prog6.storeBoundedContext.domain.PurchaseRequestCommand;
import be.kdg.prog6.storeBoundedContext.port.in.PurchaseCommand;
import be.kdg.prog6.storeBoundedContext.port.out.PaymentProcessorPort;
import com.stripe.Stripe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.stripe.model.checkout.Session;


@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentProcessorAdapter implements PaymentProcessorPort {

    private final WebClient webClient;
    @Value("${stripe.api.key}")
    private String stripeApiKey;


    @Override
    public String processPayment(List<PurchaseCommand> purchaseCommands) {

        // TODO: single item
        List<PurchaseRequestCommand> requests = purchaseCommands.stream()
                .map(command -> new PurchaseRequestCommand( command.playerId(), command.gameName()))
                .toList();

        // TODO: change to boolean
        //checking if the player dont own the game ....
        Map<Boolean, String> response = webClient.post()
                .uri("http://localhost:8090/api/games/ownership")
                .bodyValue(requests)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<Boolean, String>>() {
                })
                .block();

        //
        List<String> ownedGames = response.entrySet().stream()
                .filter(Map.Entry::getKey)
                .map(Map.Entry::getValue)
                .toList();

        // TODO: if response is true throw exception
        if (!ownedGames.isEmpty()) {
            throw new GameAlreadyOwnedException("Player already owns the following games: " + String.join(", ", ownedGames));
        }


        // TODO: change to MAP
        // Map PurchaseCommand to Stripe Line Items
        List<Map<String, Object>> lineItems = purchaseCommands.stream()
                .map(command -> Map.of(
                        "price_data", Map.of(
                                "currency", "usd",
                                "product_data", Map.of(
                                        "name", command.gameName()
                                ),
                                "unit_amount", (int) (command.gamePrice() * 100)
                        ),
                        "quantity", 1
                ))
                .toList();

        try {
            Stripe.apiKey = stripeApiKey;

            Map<String, Object> sessionParams = new HashMap<>();
            sessionParams.put("success_url", "https://yourdomain.com/success");
            sessionParams.put("cancel_url", "https://yourdomain.com/cancel");
            sessionParams.put("payment_method_types", List.of("card"));
            sessionParams.put("line_items", lineItems);
            sessionParams.put("mode", "payment");
            Session session = com.stripe.model.checkout.Session.create(sessionParams);

            return session.getUrl();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create Stripe session", e);
        }

    }
}
