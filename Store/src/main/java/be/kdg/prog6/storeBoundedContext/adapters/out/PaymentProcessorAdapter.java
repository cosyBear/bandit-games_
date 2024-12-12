package be.kdg.prog6.storeBoundedContext.adapters.out;

import be.kdg.prog6.common.exception.GameAlreadyOwnedException;
import be.kdg.prog6.storeBoundedContext.domain.PurchaseRequestCommand;
import be.kdg.prog6.storeBoundedContext.port.in.PurchaseCommand;
import be.kdg.prog6.storeBoundedContext.port.out.PaymentProcessorPort;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentProcessorAdapter implements PaymentProcessorPort {

    private final WebClient webClient;

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Override
    public String processPayment(List<PurchaseCommand> purchaseCommands) {
        // Step 1: Check if the player already owns the games
        List<PurchaseRequestCommand> requests = purchaseCommands.stream()
                .map(command -> new PurchaseRequestCommand(command.playerId(), command.gameName()))
                .toList();

        Map<Boolean, String> response = webClient.post()
                .uri("http://localhost:8090/api/games/ownership")
                .bodyValue(requests)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<Boolean, String>>() {
                })
                .block();

        List<String> ownedGames = response.entrySet().stream()
                .filter(Map.Entry::getKey)
                .map(Map.Entry::getValue)
                .toList();

        if (!ownedGames.isEmpty()) {
            throw new GameAlreadyOwnedException("Player already owns the following games: " + String.join(", ", ownedGames));
        }

        // Step 2: Create line items for Stripe checkout session
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
            // Step 3: Build metadata for multiple games
            Map<String, String> metaData = new HashMap<>();

            String gameNames = purchaseCommands.stream()
                    .map(PurchaseCommand::gameName)
                    .collect(Collectors.joining(", "));

            UUID playerId = purchaseCommands.get(0).playerId(); // Assuming all commands share the same playerId

            metaData.put("gameName", gameNames);
            metaData.put("playerId", playerId.toString());

            log.info("Metadata sent to Stripe: {}", metaData);

            // Step 4: Configure Stripe session parameters
            Map<String, Object> paymentIntentData = new HashMap<>();
            paymentIntentData.put("metadata", metaData);

            Stripe.apiKey = stripeApiKey;

            Map<String, Object> sessionParams = new HashMap<>();
            sessionParams.put("success_url", "https://yourdomain.com/success");
            sessionParams.put("cancel_url", "https://yourdomain.com/cancel");
            sessionParams.put("payment_method_types", List.of("card"));
            sessionParams.put("line_items", lineItems);
            sessionParams.put("mode", "payment");
            sessionParams.put("payment_intent_data", paymentIntentData);

            // Step 5: Create and return the session
            Session session = Session.create(sessionParams);

            return session.getUrl();
        } catch (Exception e) {
            log.error("Failed to create Stripe session", e);
            throw new RuntimeException("Failed to create Stripe session", e);
        }
    }
}