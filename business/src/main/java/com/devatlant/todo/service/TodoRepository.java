package com.devatlant.todo.service;

import com.devatlant.todo.business.entity.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @author yevgen voronetski
 */
@Component
public interface TodoRepository extends CrudRepository<Todo, Integer> {
    List<Todo> findAllByTitleLike(String searchString);
}
