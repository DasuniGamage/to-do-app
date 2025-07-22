package com.todoapp.backend.dto.mapper;

import com.todoapp.backend.dto.DtoMapper;
import com.todoapp.backend.dto.TaskDto;
import com.todoapp.backend.model.Task;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskDtoMapper implements DtoMapper<Task,TaskDto>{
    @Override
    public Task dtoToEntity(TaskDto dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCompleted(dto.isCompleted());
        task.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : LocalDateTime.now());
        return task;
    }

    @Override
    public TaskDto entityToDto(Task entity) {
        TaskDto dto = new TaskDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setCompleted(entity.isCompleted());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}
