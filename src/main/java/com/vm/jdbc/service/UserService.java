package com.vm.jdbc.service;

import com.vm.jdbc.dao.UserDao;
import com.vm.jdbc.dto.CreateUserDto;
import com.vm.jdbc.exceptions.ValidationException;
import com.vm.jdbc.mapper.CreateUserMapper;
import com.vm.jdbc.validator.CreateUserValidator;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserService {
    private final static UserService INSTANCE = new UserService();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();

    public static UserService getInstance() {
        return INSTANCE;
    }

    public Integer create(CreateUserDto createUserDto) {
        var validationResult = createUserValidator.isValid(createUserDto);
        if (!validationResult.isValid()){
            try {
                throw new ValidationException(validationResult.getErrorList());
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        }
        var user = createUserMapper.mapFrom(createUserDto);
        userDao.save(user);
        return user.getId();
    }
}
