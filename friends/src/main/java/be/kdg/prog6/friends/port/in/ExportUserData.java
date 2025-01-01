package be.kdg.prog6.friends.port.in;

import java.util.UUID;

public interface ExportUserData {

    byte[] exportUserData(UUID playerId);
}
