package be.kdg.prog6.storeBoundedContext.adapters.in;


import be.kdg.prog6.storeBoundedContext.adapters.in.dto.PurchaseDto;
import be.kdg.prog6.storeBoundedContext.port.in.PaymentUseCase;
import be.kdg.prog6.storeBoundedContext.port.in.PurchaseCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/payment")
@Slf4j
@RequiredArgsConstructor

public class PaymentController {

    private final PaymentUseCase paymentUseCase;

    @PostMapping("/checkOut")
    public ResponseEntity<String> handleCheckout(@RequestBody PurchaseDto dto) {
        final PurchaseCommand command = new PurchaseCommand(dto.gameName(), dto.gamePrice(), dto.playerId());

        return ResponseEntity.status(HttpStatus.CREATED).body(paymentUseCase.handlePayment(command));

    }


}
