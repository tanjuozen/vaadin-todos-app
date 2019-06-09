package com.tanzhu.todos.vaadintodosapp.backend.service;

import com.tanzhu.todos.vaadintodosapp.backend.data.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TodoService {

    private static final String TODO_LIST_URL = "https://jsonplaceholder.typicode.com/todos";

    private RestTemplate restTemplate;

    @Autowired
    public TodoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Call the API and cache the results
    @Cacheable("todos")
    public List<Todo> getAllTodos(){
        ResponseEntity<List<Todo>> response = restTemplate.exchange(TODO_LIST_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Todo>>() {});
        return response.getStatusCode() == HttpStatus.OK ? response.getBody() : null;
    }
}
