package cz.kdolezal.taskmanagementsystem.api;

import cz.kdolezal.taskmanagementsystem.api.request.ProjectAddRequest;
import cz.kdolezal.taskmanagementsystem.api.request.ProjectEditRequest;
import cz.kdolezal.taskmanagementsystem.domain.Project;

import java.util.List;

public interface ProjectService {
    Project get(long id);
    List<Project> getAll();
    List<Project> getAllByUser(long id);
    void delete(long id);
    long add(ProjectAddRequest request);
    void edit(long id, ProjectEditRequest request);
}
