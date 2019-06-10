package com.tanzhu.todos.vaadintodosapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@SpringBootApplication
@EnableCaching
public class VaadinTodosApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaadinTodosApplication.class, args);
	}
}
