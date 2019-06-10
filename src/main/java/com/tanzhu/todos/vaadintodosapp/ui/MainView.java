package com.tanzhu.todos.vaadintodosapp.ui;

import com.tanzhu.todos.vaadintodosapp.backend.data.Todo;
import com.tanzhu.todos.vaadintodosapp.backend.service.TodoService;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import java.util.List;

@Route
@PageTitle("Vaadin with Spring Boot Demo")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class MainView extends VerticalLayout  implements HasComponents {

    private TodoService service;


    public MainView(TodoService service) {
        this.service = service;
        final List<Todo> allTodos = fetchTodos();

        final H1 h1 = new H1("TODOs APP");
        final Grid<Todo> grid = new Grid<>();
        add(h1, grid);
        setAlignItems(Alignment.CENTER);

        grid.setItems(allTodos);

        grid.addColumn(Todo::getId)
                .setHeader("ID")
                .setSortable(true)
                .setFlexGrow(1);
        grid.addColumn(Todo::getUserId)
                .setHeader("USER_ID")
                .setSortable(true)
                .setFlexGrow(1);
        grid.addColumn(new ComponentRenderer<>(
                todo -> {
                    TextField title = new TextField();
                    title.setValue(todo.getTitle());
                    title.setWidth("80%");

                    Button update = new Button("Update", event -> {
                        todo.setTitle(title.getValue());
                        grid.getDataProvider().refreshItem(todo);
                    });

                    return new HorizontalLayout(title, update);
                }
        ))
                .setHeader("TITLE")
                .setSortable(true)
                .setFlexGrow(7);
        grid.addColumn(Todo::getCompleted)
                .setHeader("COMPLETED")
                .setSortable(true)
                .setFlexGrow(1);
    }

    private List<Todo> fetchTodos() {
        return this.service.getAllTodos();
    }
}
