package com.vm.jdbc.mapper;

import com.vm.jdbc.dto.UserDto;
import com.vm.jdbc.entity.Gender;
import com.vm.jdbc.entity.Role;
import com.vm.jdbc.entity.User;
import com.vm.jdbc.utils.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserMapper implements Mapper<UserDto, User> {
    private final static UserMapper INSTANCE = new UserMapper();

    public static UserMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public UserDto mapFrom(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .birthday(user.getBirthday())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .gender(user.getGender())
                .build();
    }
}
