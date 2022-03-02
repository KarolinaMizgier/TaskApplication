package com.crud.tasks;

import com.crud.tasks.controller.TaskController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class TasksApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(TasksApplication.class);
	}
}
