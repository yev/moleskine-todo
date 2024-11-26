package com.devatlant.todo.mapper;

import com.devatlant.todo.business.entity.Todo;
import com.devatlant.todo.model.PageToDoDTO;
import com.devatlant.todo.model.ToDoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface TodoMapper {
    ToDoDto toDto(Todo entity);
    List<ToDoDto> toListDto(List<Todo> listEntity);
    Todo fromDto(ToDoDto toDoDto);
    default PageToDoDTO toPage(Page<Todo> pageEntity){
        final com.devatlant.todo.model.PageDTO page = new com.devatlant.todo.model.PageDTO();
        page.number(pageEntity.getNumber()).size(pageEntity.getSize()).totalPages(pageEntity.getTotalPages()).totalElements((int)pageEntity.getTotalElements());
        return new PageToDoDTO().page(page).content(toListDto(pageEntity.getContent()));
    }
}
