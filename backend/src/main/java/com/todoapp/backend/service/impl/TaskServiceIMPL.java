package com.todoapp.backend.service.impl;

import com.todoapp.backend.dto.TaskDto;
import com.todoapp.backend.dto.mapper.TaskDtoMapper;
import com.todoapp.backend.model.Task;
import com.todoapp.backend.repository.TaskRepository;
import com.todoapp.backend.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceIMPL implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskDtoMapper taskDtoMapper;

    public TaskServiceIMPL(TaskRepository taskRepository, TaskDtoMapper taskDtoMapper) {
        this.taskRepository = taskRepository;
        this.taskDtoMapper = taskDtoMapper;
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {

        Task task = taskDtoMapper.dtoToEntity(taskDto);
        Task savedTask= taskRepository.save(task);

        return taskDtoMapper.entityToDto(savedTask);

    }

    @Override
    public List<TaskDto> getLatestFiveTasks() {
        return taskRepository.findTop5ByOrderByCreatedAtDesc()
                .stream()
                .map(taskDtoMapper::entityToDto)
                .toList();
    }

    @Override
    public void markTaskAsCompleted(Long taskId) {
        Task task =taskRepository.findById(taskId)
                .orElseThrow(()->new RuntimeException("Task not found with id" + taskId));
        task.setCompleted(true);
        taskRepository.save(task);
    }
}
