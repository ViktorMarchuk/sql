package com.vm.jdbc.exceptions;

public class DaoException extends RuntimeException {
    public DaoException(Throwable e) {
        super (new RuntimeException(e));
    }
}
