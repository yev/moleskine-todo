package com.devatlant.todo.service;

import com.devatlant.todo.business.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @author yevgen voronetski
 */
@Component
public interface TodoRepository extends PagingAndSortingRepository<Todo, Integer>, CrudRepository<Todo, Integer> {
    List<Todo> findAllByTitleLike(String searchString);
    Page<Todo> findAll(Pageable pageable);
}
