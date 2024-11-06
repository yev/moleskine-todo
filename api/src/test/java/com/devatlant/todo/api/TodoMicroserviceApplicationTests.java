package com.devatlant.todo.api;

import com.devatlant.todo.business.entity.Todo;
import com.devatlant.todo.service.TodoService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Testcontainers
@SpringBootTest
class TodoMicroserviceApplicationTests {

    @Autowired
    private TodoService todoService;

    @Container
    @ServiceConnection// spring-boot annotation for injecting test bean for database config
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @Test
    void should_have_both_auditable_dates() throws IOException, InterruptedException {
        final Todo todo = new Todo(null, "test", false, null, null);
        todoService.saveNew(todo);

        final var today = LocalDate.now().format(DateTimeFormatter.ISO_DATE);

        final var resSelectCreatedAt = postgres.execInContainer("psql", "-c", "select created_at from todo;", "-U", "test");
        AssertionsForClassTypes.assertThat(resSelectCreatedAt.getStdout())
            .contains("created_at")
            .contains(today);

        final var resSelectUpdatedAt = postgres.execInContainer("psql", "-c", "select updated_at from todo;", "-U", "test");
        AssertionsForClassTypes.assertThat(resSelectUpdatedAt.getStdout())
            .contains("updated_at")
            .contains(today);
    }


}
