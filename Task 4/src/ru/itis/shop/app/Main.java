package ru.itis.shop.app;

import ru.itis.shop.user.api.UserConsoleOperations;
import ru.itis.shop.user.application.UserService;
import ru.itis.shop.user.infrastructure.persistence.UserRepositoryJdbcImpl;

public class Main {
    private static final String DB_URL = "jdbc:postgresql://localhost:5433/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "qwerty007";

    public static void main(String[] args) {
        UserRepositoryJdbcImpl userRepositoryJdbcImpl = new UserRepositoryJdbcImpl(DB_URL, DB_USER, DB_PASSWORD);
        UserService userService = new UserService(userRepositoryJdbcImpl);

        UserConsoleOperations operations = new UserConsoleOperations(userService);

        while (true) {
            operations.showMenu();
        }
    }
}
