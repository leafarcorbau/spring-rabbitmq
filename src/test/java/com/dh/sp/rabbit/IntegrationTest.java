package com.dh.sp.rabbit;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

@ContextConfiguration(
        initializers = IntegrationTest.Initializer.class)
public abstract class IntegrationTest {

    static class Initializer implements
            ApplicationContextInitializer<ConfigurableApplicationContext> {

        static RabbitMQContainer rabbit = new RabbitMQContainer()
                .withExposedPorts(5672);

        private static void startContainers() {
            Startables.deepStart(Stream.of(rabbit)).join();
        }

        private static Map<String, String> createConnectionConfiguration() {
            return Map.of(
                    "spring.rabbitmq.host" , rabbit.getContainerIpAddress(),
                    "spring.rabbitmq.port" , rabbit.getMappedPort(5672).toString(),
                    "spring.rabbitmq.username",rabbit.getAdminUsername(),
                    "spring.rabbitmq.password",rabbit.getAdminPassword()
            );
        }

        @Override
        public void initialize(
                ConfigurableApplicationContext applicationContext) {

            startContainers();

            ConfigurableEnvironment environment =
                    applicationContext.getEnvironment();

            MapPropertySource testcontainers = new MapPropertySource(
                    "testcontainers",
                    (Map) createConnectionConfiguration()
            );

            environment.getPropertySources().addFirst(testcontainers);
        }
    }
}
