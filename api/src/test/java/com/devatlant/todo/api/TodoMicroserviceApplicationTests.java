package com.devatlant.todo.api;

import com.devatlant.todo.service.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.info.GitProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class TodoMicroserviceApplicationTests {

    @MockBean private TodoRepository todoRepository;
    @MockBean private GitProperties gitProperties;

	@Test
	void contextLoads() {

	}

}
