package cz.kdolezal.taskmanagementsystem.implementation.jdbc.service;

import cz.kdolezal.taskmanagementsystem.api.ProjectService;
import cz.kdolezal.taskmanagementsystem.api.TaskService;
import cz.kdolezal.taskmanagementsystem.api.UserService;
import cz.kdolezal.taskmanagementsystem.api.exception.BadRequestException;
import cz.kdolezal.taskmanagementsystem.api.request.TaskAddRequest;
import cz.kdolezal.taskmanagementsystem.api.request.TaskEditRequest;
import cz.kdolezal.taskmanagementsystem.domain.Project;
import cz.kdolezal.taskmanagementsystem.domain.Task;
import cz.kdolezal.taskmanagementsystem.domain.TaskStatus;
import cz.kdolezal.taskmanagementsystem.implementation.jdbc.repository.TaskJdbcRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("jdbc")
public class TaskServiceImpl implements TaskService {

    private final TaskJdbcRepository repository;
    private final UserService userService;
    private final ProjectService projectService;

    public TaskServiceImpl(TaskJdbcRepository repository, UserService userService, ProjectService projectService) {
        this.repository = repository;
        this.userService = userService;
        this.projectService = projectService;
    }

    @Override
    public long add(TaskAddRequest request) {
        return repository.add(request);
    }

    @Override
    public void edit(long taskId, TaskEditRequest request) {
        if(this.get(taskId) != null){
            repository.update(taskId, request);
        }
    }

    @Override
    public void changeStatus(long taskId, TaskStatus status) {
        if(this.get(taskId) != null){
            repository.updateStatus(taskId,status);
        }
    }

    @Override
    public void assign(long taskId, long projectId) {
        final Task task = this.get(taskId);
        final Project project = projectService.get(projectId);

        if (task != null && project != null) {
            if (task.getUserId() != project.getUserId()) {
                throw new BadRequestException("Task and project must belong to the same user");
            }
            repository.updateProject(taskId, projectId);
        }
    }

    @Override
    public void delete(long taskId) {
        if(this.get(taskId) != null){
            repository.delete(taskId);
        }
    }

    @Override
    public Task get(long taskId) {
        return repository.getById(taskId);
    }

    @Override
    public List<Task> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Task> getAllByUser(long userId) {
        if(userService.get(userId) != null){
            return repository.getAllByUser(userId);
        }

        return null;
    }

    @Override
    public List<Task> getAllByProject(long projectId) {
        if(projectService.get(projectId) != null){
            return repository.getAllByProject(projectId);
        }

        return null;
    }
}
