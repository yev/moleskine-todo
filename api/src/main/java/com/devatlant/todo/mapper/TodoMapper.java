package com.devatlant.todo.mapper;

import com.devatlant.todo.business.entity.Todo;
import com.devatlant.todo.model.ToDoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface TodoMapper {
    ToDoDto toDto(Todo entity);
    List<ToDoDto> toListDto(List<Todo> listEntity);
    Todo fromDto(ToDoDto toDoDto);
}
