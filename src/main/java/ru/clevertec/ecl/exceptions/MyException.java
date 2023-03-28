package ru.clevertec.ecl.exceptions;

public class MyException extends Exception {

    private final String message;
    private final String code;

    public MyException(String message, String code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}