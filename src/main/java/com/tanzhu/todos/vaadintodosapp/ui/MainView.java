package com.tanzhu.todos.vaadintodosapp.ui;

import com.tanzhu.todos.vaadintodosapp.backend.data.Todo;
import com.tanzhu.todos.vaadintodosapp.backend.service.TodoService;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route
@PageTitle("Vaadin with Spring Boot Demo")
public class MainView extends VerticalLayout  implements HasComponents {

    private final TodoService service;

    final Grid<Todo> grid;

    public MainView(TodoService service) {
        this.service = service;

        this.grid = new Grid<>(Todo.class);

        add(grid);
        add(new Button("Click to show all Todos", buttonClickEvent -> this.listTodos()));
    }

    private void listTodos() {
        grid.setItems(service.getAllTodos());
    }
}
