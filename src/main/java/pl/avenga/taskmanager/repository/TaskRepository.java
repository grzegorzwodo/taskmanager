package pl.avenga.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.avenga.taskmanager.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
