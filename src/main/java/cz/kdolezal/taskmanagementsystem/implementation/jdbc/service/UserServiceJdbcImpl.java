package cz.kdolezal.taskmanagementsystem.implementation.jdbc.service;

import cz.kdolezal.taskmanagementsystem.api.UserService;
import cz.kdolezal.taskmanagementsystem.api.request.UserAddRequest;
import cz.kdolezal.taskmanagementsystem.domain.User;
import cz.kdolezal.taskmanagementsystem.implementation.jdbc.repository.ProjectJdbcRepository;
import cz.kdolezal.taskmanagementsystem.implementation.jdbc.repository.TaskJdbcRepository;
import cz.kdolezal.taskmanagementsystem.implementation.jdbc.repository.UserJdbcRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Profile("jdbc")
public class UserServiceJdbcImpl implements UserService {

    private final UserJdbcRepository repository;
    private final ProjectJdbcRepository projectJdbcRepository;
    private final TaskJdbcRepository taskJdbcRepository;

    public UserServiceJdbcImpl(UserJdbcRepository repository, ProjectJdbcRepository projectJdbcRepository, TaskJdbcRepository taskJdbcRepository) {
        this.repository = repository;
        this.projectJdbcRepository = projectJdbcRepository;
        this.taskJdbcRepository = taskJdbcRepository;
    }

    @Override
    public long add(UserAddRequest request) {
        return repository.add(request);
    }

    @Override
    public void delete(long id) {
        if(this.get(id) != null) {
            taskJdbcRepository.deleteAllByUser(id);
            projectJdbcRepository.deleteAllByUser(id);
            repository.delete(id);
        }
    }

    @Override
    public User get(long id) {
        return repository.getById(id);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
}
