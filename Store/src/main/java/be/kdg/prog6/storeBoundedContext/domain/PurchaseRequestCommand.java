package be.kdg.prog6.storeBoundedContext.domain;

import java.util.UUID;

public record PurchaseRequestCommand(UUID playerId, String gameName) {
}
