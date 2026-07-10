package ru.itis.shop.user.application;

import ru.itis.shop.user.api.dto.UserDto;
import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(RuntimeException::new);

        return new UserDto(user.getId(), user.getEmail(), user.getProfileDescription());
    }

    public void signUp(String name, String email, String password, String profileDescription) {
        User user = new User(name, email, password, profileDescription);
        userRepository.save(user);
    }

    public boolean signIn(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return userOptional.get().getPassword().equals(password);
        } else return false;
    }

    public Optional<UserDto> findById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return Optional.of(new UserDto(user.getId(), user.getEmail(), user.getProfileDescription()));
        }

        return Optional.empty();
    }

    public Optional<UserDto> findByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return Optional.of(new UserDto(user.getId(), user.getEmail(), user.getProfileDescription()));
        }

        return Optional.empty();
    }

    public void editDescription(UserDto user, String description) {
        userRepository.editDescription(user, description);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllByProfileDescription(String description) {
        return userRepository.findAllByProfileDescription(description);
    }
}

