package be.kdg.prog6.storeBoundedContext.port.in;

import java.util.List;

public interface PaymentUseCase {

    String handlePayment(List<PurchaseCommand> purchaseCommands);

}
