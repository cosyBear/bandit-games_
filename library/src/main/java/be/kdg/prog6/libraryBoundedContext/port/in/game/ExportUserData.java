package be.kdg.prog6.libraryBoundedContext.port.in.game;

import be.kdg.prog6.libraryBoundedContext.domain.id.PlayerId;
import be.kdg.prog6.libraryBoundedContext.port.in.command.ExportUserDataCommand;

import java.util.UUID;

public interface ExportUserData {

    byte[] exportUserData(ExportUserDataCommand command);

}
