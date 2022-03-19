package com.crud.tasks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TasksApplicationTests {

	@Value( "${spring.mail.username}" )
	private String username;

	@Test
	void contextLoads() {
	}
}
