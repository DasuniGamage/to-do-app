package com.todoapp.backend.mapper;

import com.todoapp.backend.dto.TaskDto;
import com.todoapp.backend.dto.mapper.TaskDtoMapper;
import com.todoapp.backend.model.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskDtoMapperTest {

    private final TaskDtoMapper mapper =new TaskDtoMapper();

    @Test
    void entityToDto_ShouldMapCorrectly(){
        Task task =new Task(1L,"T","D",false, LocalDateTime.now());
        TaskDto dto =mapper.entityToDto(task);

        assertEquals(task.getId(),dto.getId());
        assertEquals(task.getTitle(),dto.getTitle());
    }

    @Test
    void dtoToEntity_ShouldMapCorrectly(){
        TaskDto dto =new TaskDto(null, "T","D",false,null);
        Task entity = mapper.dtoToEntity(dto);

        assertEquals(dto.getTitle(),entity.getTitle());
        assertNotNull(entity.getCreatedAt());
    }
}
