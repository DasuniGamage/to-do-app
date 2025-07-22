package com.todoapp.backend.controller;

import com.todoapp.backend.dto.TaskDto;
import com.todoapp.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto){
        TaskDto createdTask =  taskService.createTask(taskDto);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping("/latest")
    public ResponseEntity<List<TaskDto>> getLatestFiveTasks(){
        return ResponseEntity.ok(taskService.getLatestFiveTasks());
    }

    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<Void> markAsCompleted(@PathVariable Long taskId){
        taskService.markTaskAsCompleted(taskId);
        return ResponseEntity.noContent().build();
    }




}
