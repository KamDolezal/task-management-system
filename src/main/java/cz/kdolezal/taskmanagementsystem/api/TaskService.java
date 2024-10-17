package cz.kdolezal.taskmanagementsystem.api;

import cz.kdolezal.taskmanagementsystem.api.request.TaskAddRequest;
import cz.kdolezal.taskmanagementsystem.api.request.TaskEditRequest;
import cz.kdolezal.taskmanagementsystem.domain.Task;
import cz.kdolezal.taskmanagementsystem.domain.TaskStatus;

import java.util.List;

public interface TaskService {
    long add(TaskAddRequest request);
    void edit(long taskId, TaskEditRequest request);
    void changeStatus(long taskId, TaskStatus status);
    void assign(long taskId, long projectId);
    void delete(long taskId);
    Task get(long taskId);
    List<Task> getAll();
    List<Task>getAllByUser(long userId);
    List<Task>getAllByProject(long projectId);


}
