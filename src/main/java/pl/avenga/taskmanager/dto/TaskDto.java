package pl.avenga.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.avenga.taskmanager.model.Status;

@AllArgsConstructor
@Getter
public class TaskDto {
    private String title;
    private String description;
    private Status status;
}
