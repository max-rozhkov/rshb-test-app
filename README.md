# Тестовое задание от РСХБ

Написать приложение(Rest API), которое позволяет одновременно(параллельно) проводить операции по снятию денег/пополнению одного и того же счета.
Операции должны осуществляться с помощью http, состояние счетов хранить в БД, вести журнал операций в БД.
Написать тесты, которые позволят проверить данное приложение.
Технологии Java, Springboot, любая SQL база данных с поддержкой транзакцийс.

# Build

Requirements: docker

Run build:

    # mvn clean install

# Run

Run the app:

    # DB_URL=jdbc:postgresql://<hostname>:5432/<db_name> DB_USERNAME=<username> DB_PASSWORD=<password> java -jar target/rshb-test-app.jar
