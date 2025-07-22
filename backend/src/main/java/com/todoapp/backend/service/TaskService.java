package com.todoapp.backend.service;

import com.todoapp.backend.dto.TaskDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    TaskDto createTask(TaskDto taskDto);

    List<TaskDto> getLatestFiveTasks();

    void markTaskAsCompleted(Long taskId);
}
