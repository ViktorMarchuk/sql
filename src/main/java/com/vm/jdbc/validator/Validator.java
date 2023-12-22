package com.vm.jdbc.validator;

public interface Validator<T> {
    ValidateResult isValid(T t);
}
