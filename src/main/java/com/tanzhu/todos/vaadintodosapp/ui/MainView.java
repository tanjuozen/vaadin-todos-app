package com.tanzhu.todos.vaadintodosapp.ui;

import com.tanzhu.todos.vaadintodosapp.ui.todos.TodosView;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

@Route
@PageTitle("Vaadin with Spring Boot Demo")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class MainView extends VerticalLayout implements HasComponents {

    private TodosView todosView;

    @Autowired
    public MainView(TodosView todosView) {
        this.todosView = todosView;
        final H1 h1 = new H1("TODOs APP");
        setAlignItems(Alignment.CENTER);
        add(h1, this.todosView);
    }
}
