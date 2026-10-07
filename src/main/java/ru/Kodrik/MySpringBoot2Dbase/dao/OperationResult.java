package ru.Kodrik.MySpringBoot2Dbase.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OperationResult<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> OperationResult<T> success(T data) {
        return new OperationResult<>(true, "Операция выполнена успешно", data);
    }

    public static <T> OperationResult<T> success(String message, T data) {
        return new OperationResult<>(true, message, data);
    }

    public static <T> OperationResult<T> failure(String message) {
        return new OperationResult<>(false, message, null);
    }
}
