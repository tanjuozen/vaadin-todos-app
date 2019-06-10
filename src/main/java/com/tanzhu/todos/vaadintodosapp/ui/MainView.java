package com.tanzhu.todos.vaadintodosapp.ui;

import com.tanzhu.todos.vaadintodosapp.ui.todos.TodosGrid;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route
@PageTitle("Vaadin with Spring Boot Demo")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class MainView extends VerticalLayout implements HasComponents {

    public MainView() {
        final H1 h1 = new H1("TODOs APP");
        final RouterLink link = new RouterLink("Click here to see all your todos", TodosGrid.class);
        setAlignItems(Alignment.CENTER);
        add(h1, link);
    }
}
