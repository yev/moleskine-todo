package com.devatlant.todo.api;

import com.devatlant.todo.business.entity.Todo;
import com.devatlant.todo.mapper.TodoMapperImpl;
import com.devatlant.todo.model.ToDoDto;
import com.devatlant.todo.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.GitProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * slice test for api contract
 * @author yevgen voronetski
 */
@WebMvcTest()
@Import({TodoMapperImpl.class, GitProperties.class})
public class ApiContractSliceTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private TodoService service;

    @Captor ArgumentCaptor<Todo> captor;

    @Test
    public void should_pass_all_mandatory_parameters() throws Exception {
        final var todoDto = new ToDoDto("test task");
        todoDto.setIsCompleted(true);
        mvc.perform(post("/todos").contentType("application/json").content(asJsonString(todoDto)))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        //check all fields passed to DB layer
        verify(service).saveNew(captor.capture());
        Assertions.assertThat(captor.getValue().getTitle()).isEqualTo("test task");
        Assertions.assertThat(captor.getValue().getIsCompleted()).isTrue();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
