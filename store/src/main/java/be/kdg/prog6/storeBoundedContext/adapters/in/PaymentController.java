package be.kdg.prog6.storeBoundedContext.adapters.in;


import be.kdg.prog6.storeBoundedContext.adapters.in.dto.PurchaseDto;
import be.kdg.prog6.storeBoundedContext.port.in.PaymentUseCase;
import be.kdg.prog6.storeBoundedContext.port.in.PurchaseCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payment")
@Slf4j
@RequiredArgsConstructor

public class PaymentController {

    private final PaymentUseCase paymentUseCase;

    @PostMapping("/checkOut")
    public ResponseEntity<String> handleCheckout(@RequestBody PurchaseDto dto, @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getClaimAsString("UserId");

        UUID playerId = UUID.fromString(userId);

        final PurchaseCommand command = new PurchaseCommand(dto.gameName(), dto.gamePrice(), playerId);

        return ResponseEntity.status(HttpStatus.CREATED).body(paymentUseCase.handlePayment(command));

    }


}
