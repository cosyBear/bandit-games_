package be.kdg.prog6.storeBoundedContext.port.in;

public interface PaymentUseCase {

    String handlePayment(PurchaseCommand command);

}
