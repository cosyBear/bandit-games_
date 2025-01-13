package be.kdg.prog6.storeBoundedContext.domain.id;

import java.util.UUID;

public record GameId(UUID id) {
    public GameId {
        if (id == null) {
            throw new IllegalArgumentException("GameId cannot be null");
        }
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
