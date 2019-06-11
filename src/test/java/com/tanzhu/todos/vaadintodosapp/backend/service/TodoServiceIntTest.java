package com.tanzhu.todos.vaadintodosapp.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanzhu.todos.vaadintodosapp.backend.data.Todo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.Arrays;
import java.util.List;

import static com.tanzhu.todos.vaadintodosapp.util.TestData.aTodoObject;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.ExpectedCount.manyTimes;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(TodoService.class)
@AutoConfigureWebClient(registerRestTemplate=true)
public class TodoServiceIntTest {

    private static final String TODO_LIST_URL = "https://jsonplaceholder.typicode.com/todos";

    @Autowired
    private TodoService service;

    @Autowired
    private MockRestServiceServer mockRestServer;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        List<Todo> todos = Arrays.asList(aTodoObject());

        mockRestServer.expect(manyTimes(), requestTo(TODO_LIST_URL))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(objectMapper.writeValueAsString(todos), MediaType.APPLICATION_JSON));

    }

    @Test
    public void getAllTodosIntegrationTest() {
        List<Todo> actualTodos = service.getAllTodos();

        mockRestServer.verify();

        assertThat(actualTodos).isNotNull();
        assertThat(actualTodos.get(0).getId()).isEqualTo(aTodoObject().getId());
    }

}
