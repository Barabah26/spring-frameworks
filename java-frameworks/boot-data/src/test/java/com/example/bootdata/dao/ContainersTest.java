package com.example.bootdata.dao;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@ContextConfiguration(initializers = {ContainersTest.Initializer.class})
public class ContainersTest {
    private static final String DATABASE_NAME = "spring-app";

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:14.3")
            .withReuse(true)
            .withDatabaseName(DATABASE_NAME);

    /*    @DynamicPropertySource
        static void datasourceProperties(DynamicPropertyRegistry registry) {
            registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
            registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
            registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        }*/
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "CONTAINER.USERNAME=" + postgreSQLContainer.getUsername(),
                    "CONTAINER.PASSWORD=" + postgreSQLContainer.getPassword(),
                    "CONTAINER.URL=" + postgreSQLContainer.getJdbcUrl()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
