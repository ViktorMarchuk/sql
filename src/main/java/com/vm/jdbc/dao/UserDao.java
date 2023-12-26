package com.vm.jdbc.dao;

import com.vm.jdbc.entity.Gender;
import com.vm.jdbc.entity.Role;
import com.vm.jdbc.entity.User;
import com.vm.jdbc.exceptions.DaoException;
import com.vm.jdbc.utils.ConnectionManager;
import lombok.NoArgsConstructor;

import java.sql.*;
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
    private static final String SQL_GET_BY_EMAIL_PASSWORD = """
            SELECT * FROM users
            WHERE email=? AND password=?
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
            statement.setObject(3, user.getEmail());
            statement.setObject(4, user.getPassword());
            statement.setObject(5, user.getRole(),Types.OTHER);
            statement.setObject(6, user.getGender(),Types.OTHER);
            System.out.println("Executing SQL: " + statement);
            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            keys.next();
            user.setId(keys.getObject("id", Integer.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public Optional<User> findByEmailAndPassword(String email, String password) {
        User user = null;
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(SQL_GET_BY_EMAIL_PASSWORD);
            statement.setString(1, email);
            statement.setString(2, password);
            var result = statement.executeQuery();
            if (result.next()) {
                user = buildEntity(result);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(user);
    }

    private User buildEntity(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getObject("id", Integer.class))
                .name(resultSet.getObject("name", String.class))
                .birthday(resultSet.getObject("birthday", Date.class).toLocalDate())
                .email(resultSet.getObject("email", String.class))
                .password(resultSet.getObject("password", String.class))
                .role(Role.find(resultSet.getObject("role", String.class)).orElse(null))
                .gender(Gender.valueOf(resultSet.getObject("gender", String.class)))
                .build();
    }

}
