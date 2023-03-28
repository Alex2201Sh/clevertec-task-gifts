package ru.clevertec.ecl.exceptions;

import lombok.Data;

@Data
public class MyException extends Exception {

    private final String message;
    private final String code;

    public MyException(String message, String code) {
        this.message = message;
        this.code = code;
    }


}