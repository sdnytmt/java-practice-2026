package ru.itis.shop.user.application;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(String email, String password, String profileDescription) {
    }

    public boolean signIn(String email, String password) {
        return false;
    }

    public User findById(String id) {
        return null;
    }

    public User findByEmail(String email) {
        return null;
    }

    public void editDescription(User user, String description) {
        userRepository.editDescription(user, description);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
