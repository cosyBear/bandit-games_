package be.kdg.prog6.storeBoundedContext.adapters.in;


import be.kdg.prog6.storeBoundedContext.adapters.in.dto.PurchaseDto;
import be.kdg.prog6.storeBoundedContext.port.in.PaymentUseCase;
import be.kdg.prog6.storeBoundedContext.port.in.PurchaseCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/payment")
@Slf4j
@RequiredArgsConstructor
public class PaymentController {


    private final PaymentUseCase paymentUseCase;


    @PostMapping("/checkOut")
    public ResponseEntity<String> handleCheckout(@RequestBody List<PurchaseDto> dto) {

        List<PurchaseCommand> commandsList = new ArrayList<>();

        for (PurchaseDto purchaseDto : dto) {
            commandsList.add( new PurchaseCommand(purchaseDto.gameName(), purchaseDto.gamePrice(), purchaseDto.playerId()));
        }


        return ResponseEntity.status(HttpStatus.CREATED).body(paymentUseCase.handlePayment(commandsList));


    }


}
