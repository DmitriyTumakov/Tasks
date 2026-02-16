package test.task.service;

import test.task.dao.Dao;
import test.task.dao.impl.UserEntityDaoImpl;
import test.task.entity.UserEntity;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class UserService {
    private final Dao<UserEntity> userDao = new UserEntityDaoImpl();

    public List<UserEntity> getAllUsers() throws IOException {
        return userDao.findAll();
    }

    public UserEntity getUserById(int id) throws IOException {
        return userDao.findById(id);
    }

    public void createUser(String name, String email, int age) throws IOException {
        UserEntity user = new UserEntity(name, email, age, LocalDateTime.now());
        userDao.save(user);
    }

    public void updateUser(int id, String name, String email, int age) throws IOException {
        UserEntity user = userDao.findById(id);
        if (name != null) {
            user.setName(name);
        }
        if (email != null) {
            user.setEmail(email);
        }
        if (age != 0) {
            user.setAge(age);
        }
        userDao.update(user);
    }

    public void deleteUser(int id) throws IOException {
        UserEntity user = userDao.findById(id);
        userDao.delete(user);
    }
}
