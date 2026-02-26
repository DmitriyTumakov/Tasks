package dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import test.task.dao.impl.UserEntityDaoImpl;
import test.task.entity.UserEntity;

import java.io.IOException;
import java.time.LocalDateTime;

@Testcontainers
public class UserEntityDaoTest {
    private final UserEntityDaoImpl userRepository = new UserEntityDaoImpl();

    @Container
    private static final GenericContainer<?> postgres = new GenericContainer<>("postgres:11-alpine")
            .withExposedPorts(5432)
            .withEnv("POSTGRES_USER", "postgres")
            .withEnv("POSTGRES_PASSWORD", "postgres");
    String jdbcUrl = String.format("jdbc:postgresql://%s:%d/postgres",
            postgres.getHost(),
            postgres.getMappedPort(5432));

    @Test
    void findById() throws IOException {
        UserEntity expected = new UserEntity("Dmitriy", "dmitriy@mail.ru", 18, LocalDateTime.now());

        int id = userRepository.save(expected).getId();
        UserEntity result = userRepository.findById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Dmitriy", result.getName());
    }

    @Test
    void save() throws IOException {
        UserEntity expected = new UserEntity("Ilya", "ilya@mail.ru", 18, LocalDateTime.now());

        int id = userRepository.save(expected).getId();
        UserEntity result = userRepository.findById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expected.getName(), result.getName());
    }

    @Test
    void update() throws IOException {
        UserEntity notExpected = new UserEntity("Ilya", "ilya@mail.ru", 18, LocalDateTime.now());
        UserEntity expected = new UserEntity(2, "Ivan", "ivan@mail.ru", 23, LocalDateTime.now());
        int id = userRepository.save(expected).getId();

        userRepository.update(expected);
        UserEntity result = userRepository.findById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expected.getName(), result.getName());
    }

    @Test
    void delete() throws IOException {
        UserEntity notExpected = new UserEntity("Ilya", "ilya@mail.ru", 18, LocalDateTime.now());
        int id = userRepository.save(notExpected).getId();

        userRepository.delete(userRepository.findById(id));
        UserEntity result = userRepository.findById(id);

        Assertions.assertNull(result);
    }
}
