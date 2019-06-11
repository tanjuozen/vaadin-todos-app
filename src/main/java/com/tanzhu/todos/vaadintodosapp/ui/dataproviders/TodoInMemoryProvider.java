package com.tanzhu.todos.vaadintodosapp.ui.dataproviders;

import com.tanzhu.todos.vaadintodosapp.backend.data.Todo;
import com.tanzhu.todos.vaadintodosapp.backend.service.TodoService;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class TodoInMemoryProvider extends ListDataProvider<Todo> {

    private TodoService todoService;

    @Autowired
    public TodoInMemoryProvider(TodoService todoService) {
        super(todoService.getAllTodos());
        this.todoService = todoService;
    }

    public Todo updateTodo(Todo todo) {
        return this.todoService.updateTodo(todo);
    }
}
