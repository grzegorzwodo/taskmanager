package pl.avenga.taskmanager.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.avenga.taskmanager.dto.TaskDto;
import pl.avenga.taskmanager.model.Task;
import pl.avenga.taskmanager.repository.TaskRepository;

@AllArgsConstructor
@Service
public class TaskService {

    private TaskRepository taskRepository;

    public Task createTask(TaskDto taskDto) {
        return taskRepository.save(new Task(taskDto.getTitle(), taskDto.getDescription(), taskDto.getStatus()));
    }

    public Task updateTask(long id, TaskDto taskDto) {
        Task task = taskRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        taskRepository.save(task);
        return task;
    }

    public void deleteTask(long id) {
        taskRepository.delete(taskRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
}
