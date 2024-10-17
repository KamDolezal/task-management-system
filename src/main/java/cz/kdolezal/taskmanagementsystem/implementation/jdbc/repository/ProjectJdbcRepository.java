package cz.kdolezal.taskmanagementsystem.implementation.jdbc.repository;


import cz.kdolezal.taskmanagementsystem.api.exception.InternalErrorException;
import cz.kdolezal.taskmanagementsystem.api.exception.ResourceNotFoundException;
import cz.kdolezal.taskmanagementsystem.api.request.ProjectAddRequest;
import cz.kdolezal.taskmanagementsystem.api.request.ProjectEditRequest;
import cz.kdolezal.taskmanagementsystem.domain.Project;
import cz.kdolezal.taskmanagementsystem.implementation.jdbc.mapper.ProjectRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.OffsetDateTime;
import java.util.List;

@Repository
public class ProjectJdbcRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ProjectRowMapper rowMapper;

    private static final Logger logger;
    private static final String GET_ALL;
    private static final String GET_BY_ID;
    private static final String GET_ALL_BY_USER;
    private static final String INSERT;
    private static final String UPDATE;
    private static final String DELETE;
    private static final String DELETE_ALL_BY_USER;

    static{
        logger = LoggerFactory.getLogger(ProjectJdbcRepository.class);
        GET_ALL = "SELECT * FROM project";
        GET_BY_ID = "SELECT * FROM project WHERE id = ?";
        GET_ALL_BY_USER = "SELECT * FROM project WHERE user_id = ?";
        INSERT = "INSERT INTO project(id, user_id, name, description, created_at) VALUES (next value for project_id_seq, ?, ?, ?, ?)";
        UPDATE = "UPDATE project SET name = ?, description = ? WHERE id = ?";
        DELETE = "DELETE FROM project WHERE id = ?";
        DELETE_ALL_BY_USER = "DELETE FROM project WHERE user_id = ?";
    }


    public ProjectJdbcRepository(JdbcTemplate jdbcTemplate, ProjectRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    public long add(ProjectAddRequest projectAddRequest){
        try{
            final KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                    final PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                    ps.setLong(1,projectAddRequest.getUserId());
                    ps.setString(2,projectAddRequest.getName());
                    if(projectAddRequest.getDescription() != null){
                        ps.setString(3,projectAddRequest.getDescription());
                    } else{
                        ps.setNull(3, Types.VARCHAR);
                    }
                    ps.setTimestamp(4, Timestamp.from(OffsetDateTime.now().toInstant()));
                    return ps;
            }, keyHolder);

            if(keyHolder.getKey() == null){
                logger.error("Error while adding project, keyHolder.getKey() is null");
                throw new InternalErrorException("Error while adding project");
            }

            return keyHolder.getKey().longValue();
        }catch (DataAccessException e){
            logger.error("Error while adding project", e);
            throw new InternalErrorException("Error while adding project");
        }
    }

    public void update(long id, ProjectEditRequest request) {
        try{
            jdbcTemplate.update(UPDATE, request.getName(), request.getDescription(), id);
        }catch (DataAccessException e){
            logger.error("Error while updating project", e);
            throw new InternalErrorException("Error while updating projects");
        }
    }

    public void delete(long id){
        try{
            jdbcTemplate.update(DELETE, id);
        }catch (DataAccessException e){
            logger.error("Error while deleting project", e);
            throw new InternalErrorException("Error while deleting projects");
        }
    }

    public List<Project> getAll(){
        try{
            return jdbcTemplate.query(GET_ALL, rowMapper);
        } catch (DataAccessException e){
            logger.error("Error while getting all projects", e);
            throw new InternalErrorException("Error while getting all projects");
        }
    }

    public Project getById(long id){
        try{
            return jdbcTemplate.queryForObject(GET_BY_ID, rowMapper, id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Project with id: " + id + " was not found");
        }catch (DataAccessException e){
            logger.error("Problem with getById project", e);
            throw new InternalErrorException("Problem with getById project");
        }
    }

    public List<Project> getAllByUserId(long userId){
        try{
            return jdbcTemplate.query(GET_ALL_BY_USER, rowMapper, userId);
        }catch (DataAccessException e) {
            logger.error("Error while getting all projects by user id", e);
            throw new InternalErrorException("Error while getting all projects by user id");
        }
    }

    public void deleteAllByUser(long userId) {
        try {
            jdbcTemplate.update(DELETE_ALL_BY_USER, userId);
        } catch (DataAccessException e) {
            logger.error("Error while deleting all projects by user", e);
            throw new InternalErrorException("Error while deleting all projects by user");
        }
    }
}
