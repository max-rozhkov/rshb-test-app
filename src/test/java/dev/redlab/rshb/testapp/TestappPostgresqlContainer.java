package dev.redlab.rshb.testapp;

import org.testcontainers.containers.PostgreSQLContainer;

/**
 * Класс описывающий контейнер PostgreSQL для тестов в проекте.
 */
public class TestappPostgresqlContainer extends PostgreSQLContainer<TestappPostgresqlContainer> {

    private static final String IMAGE_VERSION = "postgres:11";
    private static TestappPostgresqlContainer container;

    private TestappPostgresqlContainer() {
        super(IMAGE_VERSION);
    }

    public static TestappPostgresqlContainer getInstance() {
        if (container == null) {
            container = new TestappPostgresqlContainer()
                    .withUsername("postgres")
                    .withPassword("postgres");
            container.start();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}
