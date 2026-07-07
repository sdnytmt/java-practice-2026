package ru.itis.shop.user.infrastructure.persistence;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {

    private final String db_url;

    private final String db_user;

    private final String db_password;

    public UserRepositoryJdbcImpl(String db_url, String db_user, String db_password) {
        this.db_url = db_url;
        this.db_user = db_user;
        this.db_password = db_password;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void editUserData(String user_to, String user_after) {

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void editDescription(User user, String description) {

    }

    @Override
    public List<User> findAll() {
        try (Connection connection = DriverManager.getConnection(db_url, db_user, db_password)) {

            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("select * from account")) {
                    List<User> users = new ArrayList<>();
                    while (resultSet.next()) {
                        Integer id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String email = resultSet.getString("email");
                        Integer age = resultSet.getInt("age");

                        User user = new User(id, name, email, age);  // с mapperом пока не совладал

                        users.add(user);
                    }
                    return users;
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
