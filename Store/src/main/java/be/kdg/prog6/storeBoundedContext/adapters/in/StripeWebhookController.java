package be.kdg.prog6.storeBoundedContext.adapters.in;

import be.kdg.prog6.storeBoundedContext.core.AddGameToLibraryUseCaseImp;
import be.kdg.prog6.storeBoundedContext.port.in.AddGameToLibraryUseCase;
import be.kdg.prog6.storeBoundedContext.port.in.command.AddGameToLibraryCommand;
import com.stripe.model.PaymentIntent;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import com.stripe.model.checkout.Session;

@Slf4j

@RestController
@RequestMapping("/stripe")
public class StripeWebhookController {


    private final AddGameToLibraryUseCase addGameToLibraryUseCase;

    public StripeWebhookController(AddGameToLibraryUseCase addGameToLibraryUseCase) {
        this.addGameToLibraryUseCase = addGameToLibraryUseCase;
    }
    private final String stripeSigningSecret = "whsec_lUpVlMeSf5DrN8PQ6JzaCvQ03NcFk311";

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody String payload, HttpServletRequest request) {
        String sigHeader = request.getHeader("Stripe-Signature");

        try {
            // Verify the webhook signature with the signing secret
            Event event = Webhook.constructEvent(payload, sigHeader, stripeSigningSecret);

            // Handle only 'payment_intent.succeeded' event
            if ("payment_intent.succeeded".equals(event.getType())) {
                return handlePaymentIntentSucceeded(event);
            }

            // If the event type is not 'payment_intent.succeeded', return a success message without processing.
            log.warn("Unhandled event type: {}", event.getType());
            return ResponseEntity.ok("Event type unhandled");

        } catch (SignatureVerificationException e) {
            log.error("Webhook signature verification failed", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Webhook signature verification failed");
        }
    }

    private ResponseEntity<String> handlePaymentIntentSucceeded(Event event) {
        log.info("Payment Intent succeeded");

        // Extract the payment intent object from the event
        PaymentIntent paymentIntent = (PaymentIntent) event.getData().getObject();

        String gameName = paymentIntent.getMetadata().get("name");

        if ( gameName == null) {
            log.error("Missing required metadata fields: player_id or game_name");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing required metadata fields");
        }

        try {
            UUID playerId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

            // Create the AddGameToLibraryCommand and call the use case
            AddGameToLibraryCommand gameCommand = new AddGameToLibraryCommand(playerId, gameName);
            List<AddGameToLibraryCommand> gameCommands = Collections.singletonList(gameCommand);

            // Call the use case to add games to the library
            addGameToLibraryUseCase.addGamesToLibrary(gameCommands);

            return ResponseEntity.ok("Games added to the library successfully");

        } catch (IllegalArgumentException e) {
            log.error("Invalid UUID format for playerId", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid playerId format");
        }
    }
}


