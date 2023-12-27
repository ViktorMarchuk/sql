package com.vm.jdbc.dto;

import com.vm.jdbc.entity.Gender;
import com.vm.jdbc.entity.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {
    String name;
    String birthday;
    String email;
    String password;
    String role;
    String gender;
}
