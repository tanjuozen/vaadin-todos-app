package com.tanzhu.todos.vaadintodosapp.ui.todos;

import com.tanzhu.todos.vaadintodosapp.backend.service.TodoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class TodosGridTest {

    @Mock
    private TodoService service;

    @InjectMocks
    private TodosGrid todosGridComponent;

    @Test
    public void testComponentInitialized (){
        assertThat(todosGridComponent).isNotNull();
    }

/*    @Test
    public void testGridInitialized(){
        when(service.getAllTodos()).thenReturn(Arrays.asList(TestData.aTodoObject()));


        assertThat(todosGridComponent).isNotNull();
    }*/
}