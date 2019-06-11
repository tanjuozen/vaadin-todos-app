package com.tanzhu.todos.vaadintodosapp.backend.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.tanzhu.todos.vaadintodosapp.backend.data.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class TodoService {

    private static final String TODO_LIST_URL = "https://jsonplaceholder.typicode.com/todos";

    private RestTemplate restTemplate;

    @Autowired
    public TodoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "defaultResponse")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    public List<Todo> getAllTodos() {
        log.info("Making an HTTP GET request to the URI: {}", TODO_LIST_URL);
        ResponseEntity<List<Todo>> response = restTemplate.exchange(TODO_LIST_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Todo>>() {});
        return response.getStatusCode() == HttpStatus.OK ? response.getBody() : null;
    }

    public List<Todo> defaultResponse() {
        return Arrays.asList(Todo.builder().id(0L).userId(0L).title("Default fallback todo").completed(true).build());
    }

}
