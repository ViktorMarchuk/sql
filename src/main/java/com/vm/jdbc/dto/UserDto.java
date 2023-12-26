package com.vm.jdbc.dto;

import com.vm.jdbc.entity.Gender;
import com.vm.jdbc.entity.Role;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
public class UserDto {
    int id;
    String name;
    LocalDate birthday;
    String email;
    String password;
    Role role;
    Gender gender;
}
