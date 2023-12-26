package com.vm.jdbc.validator;

import com.vm.jdbc.dto.CreateUserDto;
import com.vm.jdbc.entity.Gender;
import com.vm.jdbc.entity.Role;
import com.vm.jdbc.entity.User;
import com.vm.jdbc.utils.LocalDateFormatter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserValidator implements Validator<CreateUserDto> {
    private final static CreateUserValidator INSTANCE = new CreateUserValidator();
    private final User user = new User();

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public ValidateResult isValid(CreateUserDto createUserDto) {
        var validationResult = new ValidateResult();
        if (!LocalDateFormatter.isValid(createUserDto.getBirthday())) {
            validationResult.add(Error.of("invalid.birthday", "Birthday is invalid"));
        }
        if (Gender.find(createUserDto.getGender()).isEmpty()) {
            validationResult.add(Error.of("invalid.gender", "Gender is invalid"));
        }
        if (Role.find(createUserDto.getRole()).isEmpty()) {
            validationResult.add(Error.of("invalid.role", "Role is invalid"));
        }
        if (createUserDto.getName().isEmpty()) {
            validationResult.add(Error.of("invalid.name", "Name is invalid"));
        }
        if (createUserDto.getPassword().isEmpty()) {
            validationResult.add(Error.of("invalid.password", "Password is invalid"));
        }
        if (createUserDto.getEmail().isEmpty()) {
            validationResult.add(Error.of("invalid.email", "Email is invalid"));
        }
        return validationResult;
    }
}
