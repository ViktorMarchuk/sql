package com.vm.jdbc.mapper;

import com.vm.jdbc.dto.CreateUserDto;
import com.vm.jdbc.entity.Gender;
import com.vm.jdbc.entity.Role;
import com.vm.jdbc.entity.User;
import com.vm.jdbc.utils.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserMapper implements Mapper<User, CreateUserDto> {
    private final static CreateUserMapper INSTANCE = new CreateUserMapper();

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public User mapFrom(CreateUserDto createUserDto) {
        return User.builder()
                .name(createUserDto.getName())
                .birthday(LocalDateFormatter.format(createUserDto.getBirthday()))
                .email(createUserDto.getEmail())
                .password(createUserDto.getPassword())
                .role(Role.valueOf(createUserDto.getRole()))
                .gender(Gender.valueOf(createUserDto.getGender()))
                .build();
    }
}
