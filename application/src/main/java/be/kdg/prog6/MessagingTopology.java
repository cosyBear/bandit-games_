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

    private static final String RABBITMQ_USERNAME = "bulletcoachwhips";
    private static final String RABBITMQ_PASSWORD = "fbYfN3Nnx!F0R6e{";
    private static final String RABBITMQ_VIRTUAL_HOST = "/";
    private static final String RABBITMQ_HOST = "rabbitmq-container.happyglacier-269bf72e.northeurope.azurecontainerapps.io";
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


    private static final String addGameExchange = "AddGameExchange";
    private static final String addGameQueue = "AddGameQueue";
    private static final String addGameRoutingKey = "addGame";


    @Bean("addGameExchange")
    DirectExchange addGameExchange() {
        return new DirectExchange(addGameExchange);
    }

    @Bean
    Queue addGameQueue() {
        return new Queue(addGameQueue);
    }

    @Bean
    public Binding addGameBinding(Queue addGameQueue, @Qualifier("addGameExchange") DirectExchange addGameExchange) {
        return BindingBuilder.bind(addGameQueue).to(addGameExchange).with(addGameRoutingKey);
    }

    private static final String accountCreatedExchange = "AccountCreatedExchange";
    private static final String accountCreatedQueue = "AccountCreatedQueue";
    private static final String accountCreatedRoutingKey = "AccountCreated";

    @Bean("AccountCreatedExchange")
    DirectExchange AccountCreatedExchange() {
        return new DirectExchange(accountCreatedExchange);
    }

    @Bean
    Queue AccountCreatedQueue() {
        return new Queue(accountCreatedQueue);
    }

    @Bean
    public Binding AccountCreatedBinding(Queue AccountCreatedQueue, @Qualifier("AccountCreatedExchange") DirectExchange AccountCreatedExchange) {
        return BindingBuilder.bind(AccountCreatedQueue).to(AccountCreatedExchange).with(accountCreatedRoutingKey);
    }




    private static final String ACHIEVEMENT_EXCHANGE = "AwardPlayerAchievement";
    private static final String ACHIEVEMENT_QUEUE = "AwardPlayerAchievementQueue";
    private static final String ROUTING_KEY = "achievement";


    private static final String GAME_RESULT_EXCHANGE = "gameResultExchange";
    private static final String GAME_RESULT_QUEUE = "gameResultQueue";
    private static final String GAME_RESULT_ROUTING_KEY = "gameResult";

    @Bean(name = "achievementExchangeBean")
    DirectExchange achievementExchange() {
        // autoDelete set to false to prevent automatic deletion of the exchange
        return new DirectExchange(ACHIEVEMENT_EXCHANGE);
    }

    @Bean(name = "achievementQueueBean")
    Queue achievementQueue() {
        // Durable queue that will persist across broker restarts
        return new Queue(ACHIEVEMENT_QUEUE);
    }

    @Bean(name = "achievementBindingBean")
    Binding achievementBinding(@Qualifier("achievementQueueBean") Queue achievementQueue,
                               @Qualifier("achievementExchangeBean") DirectExchange achievementExchange) {
        return BindingBuilder.bind(achievementQueue)
                .to(achievementExchange)
                .with(ROUTING_KEY);
    }

    @Bean(name = "gameResultExchangeBean")
    DirectExchange gameResultExchange() {
        // autoDelete set to false to prevent automatic deletion of the exchange
        return new DirectExchange(GAME_RESULT_EXCHANGE);
    }

    @Bean(name = "gameResultQueueBean")
    Queue gameResultQueue() {
        // Durable queue that will persist across broker restarts
        return new Queue(GAME_RESULT_QUEUE);
    }

    @Bean(name = "gameResultBindingBean")
    Binding gameResultBinding(@Qualifier("gameResultQueueBean") Queue gameResultQueue,
                              @Qualifier("gameResultExchangeBean") DirectExchange gameResultExchange) {
        return BindingBuilder.bind(gameResultQueue)
                .to(gameResultExchange)
                .with(GAME_RESULT_ROUTING_KEY);
    }


}
