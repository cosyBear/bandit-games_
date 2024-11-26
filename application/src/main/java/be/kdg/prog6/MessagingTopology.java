package be.kdg.prog6;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class MessagingTopology {


    private static String LobbyCreatedExchange = "LobbyCreatedExchange";
    private static String LobbyCreatedQueue = "LobbyExchange";
    private static String LobbyCreatedRoutingKey = "LobbyCreatedRoutingKey";

    private static final String RABBITMQ_USERNAME = "myuser";
    private static final String RABBITMQ_PASSWORD = "mypassword";
    private static final String RABBITMQ_VIRTUAL_HOST = "/";
    private static final String RABBITMQ_HOST = "localhost";


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(RABBITMQ_HOST);
        connectionFactory.setUsername(RABBITMQ_USERNAME);
        connectionFactory.setPassword(RABBITMQ_PASSWORD);
        connectionFactory.setVirtualHost(RABBITMQ_VIRTUAL_HOST);
        return connectionFactory;
    }


    @Bean
    public DirectExchange lobbyExchange() {
        return new DirectExchange(LobbyCreatedExchange);

    }

    @Bean
    public Queue lobbyCreatedQueue() {
        return new Queue(LobbyCreatedQueue);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }


    @Bean
    public Binding lobbyCreatedBinding(Queue lobbyCreatedQueue,DirectExchange lobbyCreatedExchange) {
        return BindingBuilder.bind(lobbyCreatedQueue)
                .to(lobbyCreatedExchange).
                with(LobbyCreatedRoutingKey);
    }





}
