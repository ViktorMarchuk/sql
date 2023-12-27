package com.vm.jdbc.service;

import com.vm.jdbc.dao.UserDao;
import com.vm.jdbc.dto.CreateUserDto;
import com.vm.jdbc.dto.UserDto;
import com.vm.jdbc.exceptions.ValidationException;
import com.vm.jdbc.mapper.CreateUserMapper;
import com.vm.jdbc.mapper.UserMapper;
import com.vm.jdbc.validator.CreateUserValidator;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserService {
    private final static UserService INSTANCE = new UserService();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserMapper userMapper=UserMapper.getInstance();

    public static UserService getInstance() {
        return INSTANCE;
    }

    public Integer create(CreateUserDto createUserDto) throws ValidationException {
        var validationResult = createUserValidator.isValid(createUserDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrorList());
        }
        var user = createUserMapper.mapFrom(createUserDto);
        userDao.save(user);
        return user.getId();
    }

    public Optional<UserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password).map(userMapper::mapFrom);
    }
}