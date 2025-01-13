package be.kdg.prog6.storeBoundedContext.adapters.out;

import be.kdg.prog6.common.exception.GameAlreadyOwnedException;
import be.kdg.prog6.storeBoundedContext.adapters.out.web.GamesWebClient;
import be.kdg.prog6.storeBoundedContext.domain.PurchaseRequestCommand;
import be.kdg.prog6.storeBoundedContext.port.in.PurchaseCommand;
import be.kdg.prog6.storeBoundedContext.port.out.PaymentProcessorPort;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentProcessorAdapter implements PaymentProcessorPort {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    private final GamesWebClient gamesWebClient;

    @Override
    public String processPayment(PurchaseCommand command) {
        // Step 1: Check if the player already owns the games
        final PurchaseRequestCommand request = new PurchaseRequestCommand(command.playerId(), command.gameName());

        final Boolean response = gamesWebClient.checkOwnership(request);

        if (response) {
            throw new GameAlreadyOwnedException("Player already owns the following games: " + command.gameName());
        }

        // Step 2: Create line items for Stripe checkout session
        Map<String, Object> lineItem =
                Map.of(
                        "price_data", Map.of(
                                "currency", "usd",
                                "product_data", Map.of(
                                        "name", command.gameName()
                                ),
                                "unit_amount", (int) (command.gamePrice() * 100)
                        ),
                        "quantity", 1
                );

        try {
            // Step 3: Build metadata for multiple games
            Map<String, String> metaData = new HashMap<>();

            String gameNames = command.gameName();

            UUID playerId = command.playerId();

            metaData.put("gameName", gameNames);
            metaData.put("playerId", playerId.toString());

            log.info("Metadata sent to Stripe: {}", metaData);

            // Step 4: Configure Stripe session parameters
            Map<String, Object> paymentIntentData = new HashMap<>();
            paymentIntentData.put("metadata", metaData);

            Stripe.apiKey = stripeApiKey;

            Map<String, Object> sessionParams = new HashMap<>();
            sessionParams.put("success_url", "http://localhost:5173/success?session_id={CHECKOUT_SESSION_ID}");
            sessionParams.put("cancel_url", "https://yourdomain.com/cancel");
            sessionParams.put("payment_method_types", List.of("card"));
            sessionParams.put("line_items", List.of(lineItem));
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