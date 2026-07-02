package ru.itis.shop.user.api;

import ru.itis.shop.user.application.UserService;
import ru.itis.shop.user.domain.User;

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
            case "0": {
                System.exit(0);
            }
        }
    }

    private static void printUserMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("4. Обновить описание профиля");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.println("Сейчас будем регистрировать пользователя");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();

        userService.signUp(email, password, profileDescription);
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
        String id = scanner.nextLine();

        User founded_user = userService.findById(id);
        if (founded_user != null) {
            System.out.println("Пользователь с ID " + id + " найден: " + founded_user.getEmail());
        } else {
            System.out.println("Пользователь с ID " + id + " не найден");
        }
    }

    private void editDescription() {
        System.out.println("Вы можете отредактировать описание профиля по email");
        System.out.println("Введите email:");
        String email = scanner.nextLine();

        User founded_user = userService.findByEmail(email);
        if (founded_user != null) {
            System.out.println("Введите новое описание:");
            String description = scanner.nextLine();
            userService.editDescription(founded_user, description);
        } else {
            System.out.println("Пользователь с почтой " + email + " не найден");
        }
    }

}
