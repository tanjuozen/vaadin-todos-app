package com.tanzhu.todos.vaadintodosapp.ui.todos;

import com.tanzhu.todos.vaadintodosapp.ui.dataproviders.TodoInMemoryProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class TodosViewTest {

    @Mock
    private TodoInMemoryProvider dataProvider;

    @InjectMocks
    private TodosView todosViewComponent;

    @Test
    public void testComponentInitialized (){
        assertThat(todosViewComponent).isNotNull();
    }
}