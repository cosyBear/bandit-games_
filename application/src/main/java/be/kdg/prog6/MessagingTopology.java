package be.kdg.prog6;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingTopology {


    private static String LobbyCreatedExchange = "LobbyCreatedExchange";
    private static String LobbyCreatedQueue = "LobbyCreatedQueue";
    private static String LobbyCreatedRoutingKey = "LobbyCreatedRoutingKey";

    private static final String RABBITMQ_USERNAME = "myuser";
    private static final String RABBITMQ_PASSWORD = "mypassword";
    private static final String RABBITMQ_VIRTUAL_HOST = "/";
    private static final String RABBITMQ_HOST = "rabbitmq-container.internal.wittybeach-d611681b.northeurope.azurecontainerapps.io";


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(RABBITMQ_HOST);
        connectionFactory.setUsername(RABBITMQ_USERNAME);
        connectionFactory.setPassword(RABBITMQ_PASSWORD);
        connectionFactory.setVirtualHost(RABBITMQ_VIRTUAL_HOST);
        return connectionFactory;
    }


    @Bean(name = "lobbyCreatedExchange")
    public DirectExchange lobbyCreatedExchange() {
        return new DirectExchange(LobbyCreatedExchange);

    }

    @Bean
    public Queue lobbyCreatedQueue() {
        return new Queue(LobbyCreatedQueue);
    }



    @Bean
    public Binding lobbyCreatedBinding(Queue lobbyCreatedQueue, @Qualifier("lobbyCreatedExchange") DirectExchange lobbyCreatedExchange) {
        return BindingBuilder.bind(lobbyCreatedQueue)
                .to(lobbyCreatedExchange).
                with(LobbyCreatedRoutingKey);
    }


    private static final String joinLobbyExchange = "JOINLobbyExchange";
    private static final String joinLobbyQueue = "JOINLobbyQueue";
    private static final String routingKey = "joinLobby";


    @Bean(name = "joinLobbyExchange")
    DirectExchange joinLobbyExchange() {
        return new DirectExchange(joinLobbyExchange);
    }

    @Bean
    Queue joinLobbyQueue() {
        return new Queue(joinLobbyQueue);
    }

    @Bean
    Binding lobbyJoinBinding(Queue joinLobbyQueue, @Qualifier("joinLobbyExchange") DirectExchange joinLobbyExchange) {
        return BindingBuilder.bind(joinLobbyQueue)
                .to(joinLobbyExchange)
                .with(routingKey);
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }


}
