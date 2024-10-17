package cz.kdolezal.taskmanagementsystem.implementation.jdbc.service;

import cz.kdolezal.taskmanagementsystem.api.ProjectService;
import cz.kdolezal.taskmanagementsystem.api.request.ProjectAddRequest;
import cz.kdolezal.taskmanagementsystem.api.request.ProjectEditRequest;
import cz.kdolezal.taskmanagementsystem.domain.Project;
import cz.kdolezal.taskmanagementsystem.implementation.jdbc.repository.ProjectJdbcRepository;
import cz.kdolezal.taskmanagementsystem.implementation.jdbc.repository.TaskJdbcRepository;
import cz.kdolezal.taskmanagementsystem.implementation.jdbc.repository.UserJdbcRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("jdbc")
public class ProjectServiceJdbcImpl implements ProjectService {

    private final ProjectJdbcRepository repository;
    private final UserJdbcRepository userJdbcRepository;
    private final TaskJdbcRepository taskJdbcRepository;

    public ProjectServiceJdbcImpl(ProjectJdbcRepository repository, UserJdbcRepository userJdbcRepository, TaskJdbcRepository taskJdbcRepository) {
        this.repository = repository;
        this.userJdbcRepository = userJdbcRepository;
        this.taskJdbcRepository = taskJdbcRepository;
    }

    @Override
    public Project get(long id) {
        return repository.getById(id);
    }

    @Override
    public List<Project> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Project> getAllByUser(long userId) {
        if(userJdbcRepository.getById(userId) != null){
            return repository.getAllByUserId(userId);
        }

        // This cannot happen, because if condition above returns null, it will throw exception anyway
        return null;
    }

    @Override
    public void delete(long id) {
        if(this.get(id) != null){
            taskJdbcRepository.deleteAllByProject(id);
            repository.delete(id);
        }
    }

    @Override
    public long add(ProjectAddRequest request) {
        return repository.add(request);
    }

    @Override
    public void edit(long id, ProjectEditRequest request) {
        if(this.get(id) != null){
            repository.update(id, request);
        }
    }
}
