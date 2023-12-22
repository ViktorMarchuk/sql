package com.vm.jdbc.mapper;

public interface Mapper<T, F> {

    T mapFrom(F f);
}
