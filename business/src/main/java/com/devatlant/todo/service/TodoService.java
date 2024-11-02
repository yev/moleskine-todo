package com.devatlant.todo.service;

import com.devatlant.todo.business.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author yevgen voronetski
 */
@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Transactional
    public Todo saveNew(Todo todo){
        return todoRepository.save(todo);
    }

    public List<Todo> findAllByTitleLike(final String likeWildcardsSearch) {
        return todoRepository.findAllByTitleLike(likeWildcardsSearch);
    }

    public Optional<Todo> findById(final Integer id) {
        return todoRepository.findById(id);
    }
}
