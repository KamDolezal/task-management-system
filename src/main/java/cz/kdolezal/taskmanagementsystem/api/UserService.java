package cz.kdolezal.taskmanagementsystem.api;

import cz.kdolezal.taskmanagementsystem.api.request.UserAddRequest;
import cz.kdolezal.taskmanagementsystem.domain.User;

import java.util.List;

public interface UserService {
    long add(UserAddRequest request);
    void delete(long id);
    User get(long id);
    List<User> getAll();

}
