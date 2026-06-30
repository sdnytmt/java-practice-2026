package ru.itis.shop.user.infrastructure.persistence;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class UserFileRepository implements UserRepository {

    private final String fileName;

    public UserFileRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String id = UUID.randomUUID().toString();
            user.setId(id);
            writer.write(user.getId() + "|" +
                    user.getEmail() + "|" +
                    user.getPassword() + "|" +
                    user.getProfileDescription());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public User findById(String id) {
        User founded_user = null;
        // украл с гугла, не ИИ
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // без понятия, зачем IDE подсказывает мне поставить \\ перед |
                String[] parts = line.split("\\|");
                if (Objects.equals(parts[0], id)) {  // IDE предложила этот вариант вместо parts[0] == id почему-то
                    founded_user = new User(parts[1], parts[2], parts[3]);
                    // понимаю, что использование индексов массивов не масштабируемо,
                    // так как в случае если поменяются данные, поменяется и парсинг, но пока как избежать этого,
                    // идей нет
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return founded_user;
    }
}
