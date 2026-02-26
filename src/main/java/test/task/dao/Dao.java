package test.task.dao;

import test.task.entity.UserEntity;

import java.io.IOException;
import java.util.List;

public interface Dao<O> {
    UserEntity findById(int id) throws IOException;
    UserEntity save(O object) throws IOException;
    UserEntity update(O object) throws IOException;
    UserEntity delete(O object) throws IOException;
    List<UserEntity> findAll() throws IOException;
}
