package com.todoapp.backend.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.todoapp.backend.dto.TaskDto;
import com.todoapp.backend.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.List;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTask_ShouldReturnCreatedTask() throws Exception{
        TaskDto taskDto = new TaskDto(null,"Title","Description",false, LocalDateTime.now());
        TaskDto savedTask = new TaskDto(1L,"Title","Description",false,LocalDateTime.now());

        when(taskService.createTask(any())).thenReturn(savedTask);

        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(taskService).createTask(any());
    }

    @Test
    void getLatestFiveTasks_ShouldReturnList() throws Exception{
        List<TaskDto> tasks =List.of(new TaskDto(1L,"T1","D1",false,LocalDateTime.now()));
        when(taskService.getLatestFiveTasks()).thenReturn(tasks);

        mockMvc.perform(get("/api/tasks/latest"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(taskService).getLatestFiveTasks();
    }

    @Test
    void markAsCompleted_ShouldReturnNoContent() throws Exception{
        doNothing().when(taskService).markTaskAsCompleted(1L);

        mockMvc.perform(patch("/api/tasks/1/complete"))
                .andExpect(status().isNoContent());
        verify(taskService).markTaskAsCompleted(1L);
    }
}
