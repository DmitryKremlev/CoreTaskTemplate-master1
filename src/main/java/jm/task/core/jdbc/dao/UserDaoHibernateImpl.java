package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    Transaction transaction;
    Session session = Util.getSessionFactory().openSession();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        transaction = session.beginTransaction();
        Query createQuery = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                "(id INTEGER not NULL AUTO_INCREMENT, " +
                " name VARCHAR(255), " +
                " lastname VARCHAR(255), " +
                " age INTEGER, " +
                " PRIMARY KEY ( id ))");
        int response = createQuery.executeUpdate();
        System.out.println(response); // result : 1 (success) or 0 (failure)
        transaction.commit();
    }

    @Override
    public void dropUsersTable() {
        transaction = session.beginTransaction();
        Query deleteQuery = session.createSQLQuery("DROP TABLE IF EXISTS users");
        int response = deleteQuery.executeUpdate();
        System.out.println(response); // result : 1 (success) or 0 (failure)
        transaction.commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
    }

    @Override
    public void removeUserById(long id) {
        transaction = session.beginTransaction();
        session.delete(session.get(User.class, id));
        transaction.commit();
    }

    @Override
    public List<User> getAllUsers() {
        //List<User> usersList = (List<User>)  Util.getSessionFactory().openSession().createSQLQuery("SELECT ID, NAME, LASTNAME, AGE FROM USERS").list();
        List<User> usersList = session.createCriteria(User.class).list();
        return usersList;
    }

    @Override
    public void cleanUsersTable() {
        transaction = session.beginTransaction();
        Query deleteQuery = session.createSQLQuery("TRUNCATE TABLE USERS");
        int response = deleteQuery.executeUpdate();
        System.out.println(response); // result : 1 (success) or 0 (failure)
        transaction.commit();
    }
}
