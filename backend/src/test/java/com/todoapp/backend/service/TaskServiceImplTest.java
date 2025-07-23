package com.todoapp.backend.service;

import com.todoapp.backend.dto.TaskDto;
import com.todoapp.backend.dto.mapper.TaskDtoMapper;
import com.todoapp.backend.model.Task;
import com.todoapp.backend.repository.TaskRepository;
import com.todoapp.backend.service.impl.TaskServiceIMPL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskDtoMapper mapper;

    @InjectMocks
    private TaskServiceIMPL service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTask_ShouldReturnSavedDto() {
        TaskDto dto = new TaskDto(null, "Title", "Desc", false, null);
        Task entity = new Task();
        Task saved = new Task(1L, "Title", "Desc", false, LocalDateTime.now());
        TaskDto resultDto = new TaskDto(1L, "Title", "Desc", false, saved.getCreatedAt());

        when(mapper.dtoToEntity(dto)).thenReturn(entity);
        when(taskRepository.save(entity)).thenReturn(saved);
        when(mapper.entityToDto(saved)).thenReturn(resultDto);

        TaskDto result = service.createTask(dto);

        assertEquals(1L, result.getId());
    }

    @Test
    void getLatestFiveTasks_ShouldReturnList() {
        List<Task> tasks = List.of(new Task(1L, "T", "D", false, LocalDateTime.now()));
        when(taskRepository.findTop5ByCompletedFalseOrderByCreatedAtDesc()).thenReturn(tasks);
        when(mapper.entityToDto(any())).thenReturn(new TaskDto(1L, "T", "D", false, LocalDateTime.now()));

        List<TaskDto> result = service.getLatestFiveTasks();

        assertEquals(1, result.size());
    }

    @Test
    void markTaskAsCompleted_ShouldSetToTrue() {
        Task task = new Task(1L, "T", "D", false, LocalDateTime.now());

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        service.markTaskAsCompleted(1L);

        assertTrue(task.isCompleted());
        verify(taskRepository).save(task);
    }
}
