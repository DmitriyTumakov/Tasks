package test.task.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import test.task.dao.type.LogType;
import test.task.entity.UserEntity;
import test.task.logger.Logger;
import test.task.logger.impl.DefaultLogger;

import java.io.IOException;

public class HibernateSessionFactoryUtil {
    private static final Logger logger = new DefaultLogger();
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() throws IOException {
        if (sessionFactory == null) {
            try {
                Configuration config = new Configuration().configure();
                config.addAnnotatedClass(UserEntity.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
                sessionFactory = config.buildSessionFactory(builder.build());
            } catch (Exception e) {
                logger.log(e.getMessage(), LogType.ERROR, true);
            }
        }
        return sessionFactory;
    }
}
