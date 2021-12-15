package com.example.week4tr1.exception;

public class UserBlockedException extends Exception {
    public UserBlockedException() {
    }

    public UserBlockedException(String errDesc) {
        super(errDesc);
    }
}

