package pl.avenga.taskmanager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.avenga.taskmanager.dto.TaskDto;
import pl.avenga.taskmanager.model.Status;
import pl.avenga.taskmanager.model.Task;
import pl.avenga.taskmanager.repository.TaskRepository;
import pl.avenga.taskmanager.service.TaskService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        taskRepository.deleteAll();
    }

    @Test
    public void shouldCreateTask() throws Exception {
        mockMvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).
                content("""
                        {
                          "title": "test",
                          "description": "test desc",
                          "status": "PENDING"
                        }""")).andExpect(status().isOk());

        List<Task> tasks = taskRepository.findAll();
        assertTrue(tasks.size() == 1);
    }

    @Test
    public void shouldGetTask() throws Exception {
        taskService.createTask(new TaskDto("test-title", "test-desc", Status.PENDING));

        mockMvc.perform(get("/tasks")).andReturn();
        //TODO nie dokonczony - czas stop
        //List<Task> tasks = taskRepository.findAll();
        //assertTrue(tasks.size() == 1);
    }
}
