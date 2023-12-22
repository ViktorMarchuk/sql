package com.vm.jdbc.dao;

import com.vm.jdbc.entity.User;
import com.vm.jdbc.exceptions.DaoException;
import com.vm.jdbc.utils.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.eclipse.tags.shaded.org.apache.bcel.generic.NEW;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserDao implements Dao<Integer, User> {
    private static final UserDao INSTANCE = new UserDao();
    private static final String SQL_SAVE = """
            INSERT INTO users(name, birthday, email, password, role, gender) 
            VALUES (?,?,?,?,?,?)
            """;

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public User save(User user) {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
            statement.setObject(1, user.getName());
            statement.setObject(2, user.getBirthday());
            statement.setObject(3, user.getPassword());
            statement.setObject(4, user.getEmail());
            statement.setObject(5, user.getRole());
            statement.setObject(6, user.getGender());
            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            keys.next();
            user.setId(keys.getObject("id", Integer.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
