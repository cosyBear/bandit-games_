package be.kdg.prog6.storeBoundedContext.port.in;

import java.util.UUID;

public record PurchaseCommand(String gameName, double gamePrice, UUID playerId) {
}
