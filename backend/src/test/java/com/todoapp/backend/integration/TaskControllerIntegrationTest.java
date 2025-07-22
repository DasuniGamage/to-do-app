package com.todoapp.backend.integration;


import com.todoapp.backend.dto.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerIntegrationTest {
    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    void createAndGetLatestTasks_ShouldWork(){
        String baseUrl = "http://localhost:"+port + "/api/tasks";

        TaskDto request =new TaskDto();
        request.setTitle("Integration Test");
        request.setDescription("Full flow");
        request.setCompleted(false);

        ResponseEntity<TaskDto> response = restTemplate.postForEntity(baseUrl,request,TaskDto.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody().getId());

        ResponseEntity<TaskDto[]> getResponse =restTemplate.getForEntity(baseUrl+"/latest",TaskDto[].class);
        assertEquals(HttpStatus.OK,getResponse.getStatusCode());
        assertTrue(getResponse.getBody().length>0);
    }
}
