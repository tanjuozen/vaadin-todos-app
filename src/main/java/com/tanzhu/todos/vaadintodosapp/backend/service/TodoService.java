package com.tanzhu.todos.vaadintodosapp.backend.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.tanzhu.todos.vaadintodosapp.backend.data.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class TodoService {

    private static final String TODO_LIST_URL = "https://jsonplaceholder.typicode.com/todos";
    private static final String TODO_UPDATE_URL = "https://jsonplaceholder.typicode.com/todos/{id}";

    private RestTemplate restTemplate;

    @Autowired
    public TodoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "defaultResponse")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    public List<Todo> getAllTodos() {
        log.info("Making an HTTP GET request to the URI: {}", TODO_LIST_URL);
        ResponseEntity<List<Todo>> response = restTemplate.exchange(TODO_LIST_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Todo>>() {});
        return response.getStatusCode() == HttpStatus.OK ? response.getBody() : null;
    }

    @HystrixCommand(fallbackMethod = "defaultPUTResponse")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    public Todo updateTodo(Todo todo) {
        log.info("Making an HTTP PUT request to the URI: {} for todoId: {}", TODO_UPDATE_URL, todo.getId());
        this.restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HashMap<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(todo.getId()));
        final HttpEntity<Todo> requestEntity = new HttpEntity<>(todo, headers);

        ResponseEntity<Todo> response = restTemplate.exchange(TODO_UPDATE_URL,
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<Todo>() {},
                params);
        return response.getStatusCode() == HttpStatus.OK ? response.getBody() : null;
    }


    public List<Todo> defaultResponse() {
        return Arrays.asList(Todo.builder().id(0L).userId(0L).title("Default fallback todo").completed(true).build());
    }

    public Todo defaultPUTResponse(Todo todo) {
        return Todo.builder().id(0L).userId(0L).title("Default fallback todo").completed(true).build();
    }

}
