package ru.itis.shop.user.api;

import ru.itis.shop.user.api.dto.UserDto;
import ru.itis.shop.user.application.UserService;
import ru.itis.shop.user.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserConsoleOperations {

    private final UserService userService;
    private final Scanner scanner;

    public UserConsoleOperations(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        printUserMenu();

        String command = scanner.nextLine();

        switch (command) {
            case "1": {
                signUp();
            }
            break;
            case "2": {
                signIn();
            }
            case "3": {
                findById();
            }
            break;
            case "4": {
                editDescription();
            }
            break;
            case "5": {
                findAll();
            }
            break;
            case "6": {
                findByProfileDescription();
            }
            break;
            case "7": {
                String email = scanner.nextLine();
                UserDto user = userService.getUserByEmail(email);
                System.out.println(user.getId() + " " + user.getProfileDescription() + " ");
            }

            case "0": {
                System.exit(0);
            }
        }
    }

    private static void printUserMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("4. Обновить описание пользователя по почте");
        System.out.println("5. Получить информацию обо всех пользователях");
        System.out.println("6. Показать информацию о пользователях с заданным описанием профиля");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.println("Сейчас будем регистрировать пользователя");
        System.out.println("Введите name:");
        String name = scanner.nextLine();
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();

        userService.signUp(name, email, password, profileDescription);
    }


    private void signIn() {
        System.out.println("Вы можете войти в приложение");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();

        if (userService.signIn(email, password)) {
            System.out.println("Вы вошли в приложение");
        } else {
            System.out.println("Email или пароль не верны");
        }
    }

    private void findById() {
        System.out.println("Сейчас будем искать пользователя по ID");
        System.out.println("Введите id:");
        Integer id = Integer.valueOf(scanner.nextLine());

        Optional<UserDto> userOptional = userService.findById(id);

        if (userOptional.isPresent()) {
            UserDto founded_user = userOptional.get();
            System.out.println("Пользователь с ID " + id + " найден: " + founded_user.getEmail());
        } else {
            System.out.println("Пользователь с ID " + id + " не найден");
        }
    }

    private void editDescription() {
        System.out.println("Вы можете отредактировать описание профиля по email");
        System.out.println("Введите email:");
        String email = scanner.nextLine();

        Optional<UserDto> userOptional = userService.findByEmail(email);

        if (userOptional.isPresent()) {
            UserDto founded_user = userOptional.get();
            System.out.println("Введите новое описание:");
            String description = scanner.nextLine();
            userService.editDescription(founded_user, description);
        } else {
            System.out.println("Пользователь с почтой " + email + " не найден");
        }
    }

    private void findByProfileDescription() {
        System.out.println("Введите описание профиля для поиска:");
        String description = scanner.nextLine();

        List<User> users = userService.findAllByProfileDescription(description);

        if (users.isEmpty()) {
            System.out.println("Пользователи с описанием '" + description + "' не найдены.");
        } else {
            System.out.println("Найденные пользователи:");
            for (User user : users) {
                System.out.println(user.getName() + " " + user.getEmail());
            }
        }
    }

    private void findAll() {
        System.out.println("Пользователи:");
        List<User> users = userService.findAll();

        for (User user : users) {
            System.out.println(user.getName() + " | " + user.getEmail());
        }
    }

}
