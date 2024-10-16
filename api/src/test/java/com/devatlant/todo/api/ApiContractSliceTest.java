package com.devatlant.todo.api;

import com.devatlant.todo.business.entity.Todo;
import com.devatlant.todo.mapper.TodoMapperImpl;
import com.devatlant.todo.model.ToDoDto;
import com.devatlant.todo.service.TodoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.GitProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author yevgen voronetski
 */
@WebMvcTest()
@Import({TodoMapperImpl.class})
public class ApiContractSliceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TodoRepository todoRepository;
    @MockBean
    private GitProperties gitProperties;
    @Captor ArgumentCaptor<Todo> captor;

    @Test
    public void should_pass_all_mandatory_parameters() throws Exception {
        var todoDto = new ToDoDto("test task", true);
        mvc.perform(post("/todos").contentType("application/json").content(asJsonString(todoDto)))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        //check all fields passed to DB layer
        verify(todoRepository).save(captor.capture());
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
