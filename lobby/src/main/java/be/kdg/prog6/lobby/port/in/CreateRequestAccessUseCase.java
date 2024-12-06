package be.kdg.prog6.lobby.port.in;

import be.kdg.prog6.lobby.port.in.command.CreateRequestAccessCommand;

public interface CreateRequestAccessUseCase {




    String createRequest(CreateRequestAccessCommand command);


}
