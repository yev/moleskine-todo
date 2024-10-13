package com.devatlant.todo.api;

import com.devatlant.todo.business.entity.Todo;
import com.devatlant.todo.mapper.TodoMapper;
import com.devatlant.todo.model.CreateTodo201Response;
import com.devatlant.todo.model.CreateTodoRequest;
import com.devatlant.todo.model.ToDoDto;
import com.devatlant.todo.service.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author yevgen voronetski
 */
@RequiredArgsConstructor
@RestController
@Slf4j
public class Controller implements TodosApi {
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    @Override
    @Transactional
    public ResponseEntity<CreateTodo201Response> createTodo(CreateTodoRequest createTodoRequest) {
        var todoEntity = new Todo();
        todoEntity.setTitle(createTodoRequest.getTitle());
        var savedTodo = todoRepository.save(todoEntity);
        return ResponseEntity.ok(new CreateTodo201Response().id(savedTodo.getId()).title(savedTodo.getTitle()));
    }

    @Override
    public ResponseEntity<List<ToDoDto>> searchTodosByTitle(final String title) {
        var likeWildcardsSearch = "%"+title+"%";
        log.info("title with wildcard '{}'", likeWildcardsSearch);
        return ResponseEntity.ok(todoMapper.toListDto(todoRepository.findAllByTitleLike(likeWildcardsSearch)));
    }
}
