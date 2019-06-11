package com.tanzhu.todos.vaadintodosapp.ui.todos;

import com.tanzhu.todos.vaadintodosapp.backend.data.Todo;
import com.tanzhu.todos.vaadintodosapp.ui.dataproviders.TodoInMemoryProvider;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

@Route(TodosView.NAME)
@Theme(value = Lumo.class, variant = Lumo.DARK)
@SpringComponent
@UIScope
public class TodosView extends VerticalLayout {

    static final String NAME = "todos";

    private final Grid<Todo> grid = new Grid<>();
    private TodoInMemoryProvider dataProvider;

    @Autowired
    public TodosView(TodoInMemoryProvider dataProvider) {
        this.dataProvider = dataProvider;

        add(grid);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

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

        grid.setDataProvider(this.dataProvider);
    }
}
