package com.tanzhu.todos.vaadintodosapp.ui.todos;

import com.tanzhu.todos.vaadintodosapp.backend.data.Todo;
import com.tanzhu.todos.vaadintodosapp.backend.service.TodoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import java.util.List;

@Route(TodosGrid.NAME)
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class TodosGrid extends VerticalLayout {

    public static final String NAME = "todos";

    final Grid<Todo> grid = new Grid<>();
    private TodoService service;

    public TodosGrid(TodoService service) {
        this.service = service;
        final List<Todo> allTodos = fetchTodos();

        add(grid);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

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

                    Button update = new Button("Update", buttonClickEvent -> {
                        todo.setTitle(title.getValue());
                        grid.getDataProvider().refreshItem(todo);
                        Notification.show("Todo with id { " + todo.getId() + " } updated");
                    });

                    return new HorizontalLayout(title, update);
                    }
                ))
                .setHeader("TITLE")
                .setComparator(Todo::getTitle)
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
