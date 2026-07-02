package ru.itis.shop.user.repository;

import ru.itis.shop.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    void editUserData(String user_to, String user_after);

    Optional<User> findByEmail(String email);

    Optional<User> findById(String id);

    void editDescription(User user, String description);
}
