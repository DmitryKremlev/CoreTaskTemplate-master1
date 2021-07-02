package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Kolt", "Nesterov", (byte) 24);
        userService.saveUser("Matt", "Daimon", (byte) 48);
        userService.saveUser("Tolya", "Batareikin", (byte) 88);
        userService.saveUser("Michael", "Jordan", (byte) 58);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
