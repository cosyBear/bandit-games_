package be.kdg.prog6.storeBoundedContext.port.out;

import be.kdg.prog6.storeBoundedContext.port.in.PurchaseCommand;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface PaymentProcessorPort {

    String processPayment(PurchaseCommand command);



}
