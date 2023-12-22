package com.vm.jdbc.validator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidateResult {
    @Getter
    private final List<Error> errorList = new ArrayList<>();

    public void add(Error error) {
        errorList.add(error);
    }

    public boolean isValid() {
        return errorList.isEmpty();
    }
}
