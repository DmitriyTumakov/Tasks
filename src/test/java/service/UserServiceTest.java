package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import test.task.dao.impl.UserEntityDaoImpl;
import test.task.entity.UserEntity;
import test.task.service.UserService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserEntityDaoImpl userEntityDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllUsers() throws IOException {
        int arraySize = 4;
        Mockito.when(userEntityDao.findAll()).thenReturn(List.of(new UserEntity(),
                new UserEntity(),
                new UserEntity(),
                new UserEntity()));

        List<UserEntity> result = userService.getAllUsers();

        Assertions.assertEquals(arraySize, result.size());
    }

    @Test
    public void getUserById() throws IOException {
        UserEntity userEntity = new UserEntity(1,
                "Dmitriy",
                "dmitriy@mail.ru",
                18, LocalDateTime.now());
        Mockito.when(userEntityDao.findById(1)).thenReturn(userEntity);

        UserEntity result = userService.getUserById(1);

        Assertions.assertEquals(userEntity.getName(), result.getName());
        Assertions.assertEquals(userEntity.getEmail(), result.getEmail());
        Assertions.assertEquals(userEntity.getAge(), result.getAge());
    }

    @Test
    public void createUser() throws IOException {
        UserEntity userEntity = new UserEntity(1,
                "Dmitriy",
                "dmitriy@mail.ru",
                18, LocalDateTime.now());
        Mockito.when(userEntityDao.save(userEntity)).thenReturn(userEntity);

        UserEntity result = userService.createUser("Dmitriy", "dmitriy@mail.ru", 18);

        Assertions.assertEquals(userEntity.getName(), result.getName());
        Assertions.assertEquals(userEntity.getEmail(), result.getEmail());
        Assertions.assertEquals(userEntity.getAge(), result.getAge());
    }

    @Test
    public void updateUser() throws IOException {
        LocalDateTime now = LocalDateTime.now();
        UserEntity userEntity = new UserEntity(1,
                "Dmitriy",
                "dmitriy@mail.ru",
                18, now);
        UserEntity updatedUser = new UserEntity(1,
                "Ivan",
                "ivan@mail.ru",
                19, now);
        Mockito.when(userEntityDao.findById(1)).thenReturn(userEntity);
        Mockito.when(userEntityDao.update(updatedUser)).thenReturn(userEntity);

        UserEntity result = userService.updateUser(1, updatedUser.getName(), updatedUser.getEmail(), updatedUser.getAge());

        Assertions.assertEquals(updatedUser.getName(), result.getName());
        Assertions.assertEquals(updatedUser.getEmail(), result.getEmail());
        Assertions.assertEquals(updatedUser.getAge(), result.getAge());
    }

    @Test
    public void deleteUser() throws IOException {
        UserEntity userEntity = new UserEntity(1,
                "Dmitriy",
                "dmitriy@mail.ru",
                18, LocalDateTime.now());
        Mockito.when(userEntityDao.delete(userEntity)).thenReturn(userEntity);
        Mockito.when(userEntityDao.findById(1)).thenReturn(null);

        userService.deleteUser(1);

        UserEntity result = userService.getUserById(1);

        Assertions.assertNull(result);
    }
}
