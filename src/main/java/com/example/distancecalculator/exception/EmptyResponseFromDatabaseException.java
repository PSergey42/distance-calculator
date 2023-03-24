package com.example.distancecalculator.exception;

public class EmptyResponseFromDatabaseException extends RuntimeException {

    public EmptyResponseFromDatabaseException(String mes) {
        super(mes);
    }
}
