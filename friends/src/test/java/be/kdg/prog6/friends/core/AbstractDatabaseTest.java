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
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest @ExtendWith(SpringExtension.class)
@Testcontainers @ContextConfiguration(initializers = AbstractDatabaseTest.DataSourceInitializer.class)
@Slf4j
public abstract class AbstractDatabaseTest {

    @Container
    private static final MySQLContainer<?> DATABASE = new MySQLContainer<>("mysql:8.0.30")
            .withDatabaseName("friends")
            .withUsername("user")
            .withPassword("password");
    @BeforeAll
    static void startContainer() {
        DATABASE.start();
        Assertions.assertTrue(DATABASE.isRunning(), "Database container should be running");
    }

    @AfterEach
    void printContainerLogs() {
        System.out.println("Container logs:\n" + DATABASE.getLogs());
    }

    public static class DataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    applicationContext,
                    "spring.datasource.url=" + DATABASE.getJdbcUrl(),
                    "spring.datasource.username=" + DATABASE.getUsername(),
                    "spring.datasource.password=" + DATABASE.getPassword(),
                    "spring.jpa.hibernate.ddl-auto=create-drop" // Ensure fresh schema for each test
            );
        }
    }
}
