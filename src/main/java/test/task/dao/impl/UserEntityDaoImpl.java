package test.task.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import test.task.dao.Dao;
import test.task.dao.type.LogType;
import test.task.entity.UserEntity;
import test.task.logger.Logger;
import test.task.logger.impl.DefaultLogger;
import test.task.util.HibernateSessionFactoryUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserEntityDaoImpl implements Dao<UserEntity> {
    private static final Logger logger = new DefaultLogger();

    @Override
    public UserEntity findById(int id) throws IOException {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            UserEntity userEntity = session.get(UserEntity.class, id);
            if (userEntity == null) {
                logger.log("Пользователь " + id + " не найден", LogType.INFO, true);
            } else {
                logger.log("Пользователь " + id + " найден", LogType.INFO, true);
            }
            return userEntity;
        } catch (Exception e) {
            logger.log(e.getMessage(), LogType.ERROR, true);
        }
        return null;
    }

    @Override
    public void save(UserEntity user) throws IOException {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            logger.log("Пользователь " + user.getId() + " создан", LogType.INFO, true);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(e.getMessage(), LogType.ERROR, true);
        }
    }

    @Override
    public void update(UserEntity user) throws IOException {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
            logger.log("Информация о пользователе " + user.getId() + " обновлена", LogType.INFO, true);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(e.getMessage(), LogType.ERROR, true);
        }
    }

    @Override
    public void delete(UserEntity user) throws IOException {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
            logger.log("Пользователь " + user.getId() + " удалён", LogType.INFO, true);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(e.getMessage(), LogType.ERROR, true);
        }
    }

    @Override
    public List<UserEntity> findAll() throws IOException {
        List<UserEntity> list = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            list = session.createQuery("FROM " + UserEntity.class.getName()).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(e.getMessage(), LogType.ERROR, true);
        }
        logger.log("Выведен список всех пользователей", LogType.INFO, true);
        return list;
    }
}
