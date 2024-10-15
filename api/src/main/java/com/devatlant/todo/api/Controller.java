package com.devatlant.todo.api;

import com.devatlant.todo.mapper.TodoMapper;
import com.devatlant.todo.model.ApiErrorItem;
import com.devatlant.todo.model.ToDoDto;
import com.devatlant.todo.service.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.GitProperties;
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
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    @Autowired
    private final GitProperties gitProperties;

    @Override
    @Transactional
    public ResponseEntity<ToDoDto> createTodo(final ToDoDto createTodoRequest) {
        var savedTodo = todoRepository.save(todoMapper.fromDto(createTodoRequest));
        return ResponseEntity.ok(todoMapper.toDto(savedTodo));
    }

    @Override
    public ResponseEntity<List<ToDoDto>> searchTodosByTitle(final String title) {
        var likeWildcardsSearch = "%"+title+"%";
        log.info("title with wildcard '{}'", likeWildcardsSearch);
        return ResponseEntity.ok(todoMapper.toListDto(todoRepository.findAllByTitleLike(likeWildcardsSearch)));
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
