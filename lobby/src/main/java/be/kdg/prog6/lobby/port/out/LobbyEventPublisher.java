package be.kdg.prog6.lobby.port.out;


import be.kdg.prog6.lobby.port.in.LobbyCreatedEvent;

public interface LobbyEventPublisher
{


    void publishLobbyCreatedEvent(LobbyCreatedEvent event);

}
