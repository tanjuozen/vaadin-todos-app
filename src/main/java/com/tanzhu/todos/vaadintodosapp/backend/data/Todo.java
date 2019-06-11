package com.tanzhu.todos.vaadintodosapp.backend.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Todo implements Serializable {

    private Long userId;
    private Long id;
    private String title;
    private Boolean completed;
}
