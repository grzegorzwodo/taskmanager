package pl.avenga.taskmanager.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.avenga.taskmanager.dto.TaskDto;
import pl.avenga.taskmanager.model.Task;
import pl.avenga.taskmanager.repository.TaskRepository;
import pl.avenga.taskmanager.service.TaskService;


@RestController()
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private TaskRepository taskRepository;
    private TaskService taskService;

    @GetMapping()
    public Page<Task> getTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @PostMapping()
    public Task createTask(@RequestBody TaskDto task) { //TODO validate
        return taskService.createTask(task);
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable long id) {
        return taskRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable long id, @RequestBody TaskDto task) {

        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id) {
        taskService.deleteTask(id);
    }
}
