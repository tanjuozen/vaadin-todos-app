package com.tanzhu.todos.vaadintodosapp.backend.service;

import com.tanzhu.todos.vaadintodosapp.backend.data.Todo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static com.tanzhu.todos.vaadintodosapp.util.TestData.aTodoObject;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {
    private static final String TODO_LIST_URL = "https://jsonplaceholder.typicode.com/todos";

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TodoService todoService;

    @Test
    public void getAllTodos() {
        List<Todo> todos = Arrays.asList(aTodoObject());
        when(restTemplate.exchange(TODO_LIST_URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Todo>>() {})).thenReturn(new ResponseEntity<>(todos, HttpStatus.OK));

        List<Todo> actual = todoService.getAllTodos();
        assertThat(actual).isNotNull();
        assertThat(actual.get(0)).isEqualTo(aTodoObject());
    }
}