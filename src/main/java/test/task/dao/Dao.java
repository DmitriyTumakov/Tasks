package test.task.dao;

import test.task.entity.UserEntity;

import java.io.IOException;
import java.util.List;

public interface Dao<O> {
    UserEntity findById(int id) throws IOException;
    void save(O object) throws IOException;
    void update(O object) throws IOException;
    void delete(O object) throws IOException;
    List<UserEntity> findAll() throws IOException;
}
