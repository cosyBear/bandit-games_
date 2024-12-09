package be.kdg.prog6.storeBoundedContext.core;

import be.kdg.prog6.common.events.util.GameNotFoundException;
import be.kdg.prog6.storeBoundedContext.domain.Store;
import be.kdg.prog6.storeBoundedContext.port.in.PaymentUseCase;
import be.kdg.prog6.storeBoundedContext.port.in.PurchaseCommand;
import be.kdg.prog6.storeBoundedContext.port.out.GameLoadPort;
import be.kdg.prog6.storeBoundedContext.port.out.PaymentProcessorPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Slf4j
@Service
public class PaymentUseCaseImp implements PaymentUseCase {

    private final PaymentProcessorPort processorPayment;
    private final GameLoadPort gameLoadPort;


    @Override
    @Transactional
    public String handlePayment(List<PurchaseCommand> purchaseCommands) {

        log.info("processing payment");
        Store store = gameLoadPort.loadAllAvailGames();

        boolean allGamesExist = purchaseCommands.stream()
                .allMatch(purchaseCommand -> store.containsGame(purchaseCommand.gameName())); // Ensure all games exist

        if (!allGamesExist) {
            throw new GameNotFoundException("One or more games are not available in the store.");
        }

        return processorPayment.processPayment(purchaseCommands);


    }
}
