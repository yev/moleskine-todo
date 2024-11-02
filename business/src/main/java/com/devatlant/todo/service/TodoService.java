package com.devatlant.todo.service;

import com.devatlant.todo.business.entity.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author yevgen voronetski
 */
@Service
@Slf4j
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Transactional
    public Todo saveNew(final Todo todo){
        final var saved = todoRepository.save(todo);
        log.info("just saved : {}", saved);
        return saved;
    }

    public List<Todo> findAllByTitleLike(final String likeWildcardsSearch) {
        return todoRepository.findAllByTitleLike(likeWildcardsSearch);
    }


    @Transactional
    public Optional<Todo> update(Todo todo){
        final var entityOpt = todoRepository.findById(todo.getId());
        if (entityOpt.isEmpty()){
            log.error("todo with id {} is not found in db", todo.getId());
            return Optional.empty();
        }
        return Optional.of(todoRepository.save(entityOpt.get()));
    }

}
