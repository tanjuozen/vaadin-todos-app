package com.tanzhu.todos.vaadintodosapp.util;

import com.tanzhu.todos.vaadintodosapp.backend.data.Todo;

public class TestData {

    public static Todo aTodoObject() {
        return Todo.builder()
                .id(1L)
                .userId(1L)
                .title("test")
                .completed(false)
                .build();
    }
}
