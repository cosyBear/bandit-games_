package be.kdg.prog6.storeBoundedContext.port.out;

import be.kdg.prog6.storeBoundedContext.port.in.PurchaseCommand;

import java.util.List;

public interface PaymentProcessorPort {

    String processPayment(List<PurchaseCommand> purchaseCommands);



}
