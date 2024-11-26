package com.devatlant.todo.api;

import com.devatlant.todo.mapper.TodoMapper;
import com.devatlant.todo.model.ApiErrorItem;
import com.devatlant.todo.model.PageToDoDTO;
import com.devatlant.todo.model.ToDoDto;
import com.devatlant.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.GitProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yevgen voronetski
 */
@RequiredArgsConstructor
@RestController
@Slf4j
public class Controller implements TodosApi {
    private final TodoMapper todoMapper;
    private final TodoService service;

    @Autowired
    private final GitProperties gitProperties;

    @Override
    public ResponseEntity<ToDoDto> createTodo(final ToDoDto createTodoRequest) {
        var savedTodo = service.saveNew(todoMapper.fromDto(createTodoRequest));
        return ResponseEntity.ok(todoMapper.toDto(savedTodo));
    }

    @Override
    public ResponseEntity<List<ToDoDto>> searchTodosByTitle(final String title) {
        var likeWildcardsSearch = "%"+title+"%";
        log.info("title with wildcard '{}'", likeWildcardsSearch);
        return ResponseEntity.ok(todoMapper.toListDto(service.findAllByTitleLike(likeWildcardsSearch)));
    }

    @Override
    public ResponseEntity<PageToDoDTO> findAll(Pageable pageable) {
        return ResponseEntity.ok(todoMapper.toPage(service.findAll(pageable)));
    }

    @Override
    public ResponseEntity<ToDoDto> updateTodo(final ToDoDto toDoDto) {
        final var updatedTodoOpt = service.update(todoMapper.fromDto(toDoDto));
        return updatedTodoOpt
            .map(todo -> ResponseEntity.ok(todoMapper.toDto(todo)))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(toDoDto));
    }

    @Override
    public ResponseEntity<ToDoDto> getTodoById(Integer id) {
        final var todoEntityOpt = service.findBy(id);
        return todoEntityOpt
            .map(todo -> ResponseEntity.ok(todoMapper.toDto(todo)))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ApiErrorItem> handleValidationExceptions(
        MethodArgumentNotValidException ex) {
        final var errors = new ArrayList<ApiErrorItem>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(new ApiErrorItem(fieldName, errorMessage, gitProperties.getShortCommitId()));
        });
        return errors;
    }
}
