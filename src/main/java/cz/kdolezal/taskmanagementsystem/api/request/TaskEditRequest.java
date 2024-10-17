package cz.kdolezal.taskmanagementsystem.api.request;

import cz.kdolezal.taskmanagementsystem.domain.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskEditRequest {
    private String name;
    private String description;
    private TaskStatus status;
}
