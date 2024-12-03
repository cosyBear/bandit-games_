package be.kdg.prog6.friends.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Testcontainers
@ContextConfiguration(initializers = AbstractDatabaseAndRabbitTest.DataSourceAndRabbitInitializer.class)
@Slf4j
public abstract class AbstractDatabaseAndRabbitTest {

    @Container
    private static final MySQLContainer<?> DATABASE = new MySQLContainer<>("mysql:8.0.30")
            .withDatabaseName("friends")
            .withUsername("user")
            .withPassword("password");

    @Container
    private static final RabbitMQContainer RABBIT_MQ = new RabbitMQContainer("rabbitmq:3.11-management")
            .withExposedPorts(5672, 15672) // 5672: AMQP protocol, 15672: management UI
            .withUser("guest", "guest");

    @BeforeAll
    static void startContainers() {
        DATABASE.start();
        RABBIT_MQ.start();
        Assertions.assertTrue(DATABASE.isRunning(), "Database container should be running");
        Assertions.assertTrue(RABBIT_MQ.isRunning(), "RabbitMQ container should be running");
    }

    @AfterEach
    void printContainerLogs() {
        System.out.println("Database Container logs:\n" + DATABASE.getLogs());
        System.out.println("RabbitMQ Container logs:\n" + RABBIT_MQ.getLogs());
    }

    public static class DataSourceAndRabbitInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    applicationContext,
                    "spring.datasource.url=" + DATABASE.getJdbcUrl(),
                    "spring.datasource.username=" + DATABASE.getUsername(),
                    "spring.datasource.password=" + DATABASE.getPassword(),
                    "spring.jpa.hibernate.ddl-auto=create-drop", // Ensure fresh schema for each test
                    "spring.rabbitmq.host=" + RABBIT_MQ.getHost(),
                    "spring.rabbitmq.port=" + RABBIT_MQ.getAmqpPort(),
                    "spring.rabbitmq.username=" + RABBIT_MQ.getAdminUsername(),
                    "spring.rabbitmq.password=" + RABBIT_MQ.getAdminPassword()
            );
        }
    }
}
