package com.tanzhu.todos.vaadintodosapp;

import com.tanzhu.todos.vaadintodosapp.backend.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@SpringBootApplication
@EnableCaching
public class VaadinTodosApplication {

	@Autowired
	private TodoService service;

	public static void main(String[] args) {
		SpringApplication.run(VaadinTodosApplication.class, args);
	}
/*
	@Bean
	public CommandLineRunner run() {
		return args -> {
			log.info("Calling service...");
			service.getAllTodos().forEach(System.out::println);
		};
	}*/
}
