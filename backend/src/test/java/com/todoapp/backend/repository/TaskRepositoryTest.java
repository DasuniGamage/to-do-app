package com.todoapp.backend.repository;

import com.todoapp.backend.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TaskRepositoryTest {
    @Autowired
    private TaskRepository taskRepository;

    @Test
    void findTop5ByCompletedFalseOrderByCreatedAtDesc_ShouldReturnLatestUncompletedTasks(){
        // Save 10 tasks: 5 completed, 5 uncompleted
        for(int i=0; i<10 ; i++){
            boolean completed = i < 5; // first 5 are completed
            Task t = new Task(null,"T"+i, "D"+i , false, LocalDateTime.now().minusMinutes(i));
            taskRepository.save(t);
        }
        List<Task> tasks = taskRepository.findTop5ByCompletedFalseOrderByCreatedAtDesc();
        assertEquals(5, tasks.size());
        // Check that all are uncompleted
        assertTrue(tasks.stream().allMatch(t -> !t.isCompleted()));

        // Check that the first task is newer than the last
        assertTrue(tasks.get(0).getCreatedAt().isAfter(tasks.get(4).getCreatedAt()));
    }
}
