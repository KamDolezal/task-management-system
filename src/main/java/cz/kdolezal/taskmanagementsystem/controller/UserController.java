package cz.kdolezal.taskmanagementsystem.controller;

import cz.kdolezal.taskmanagementsystem.api.UserService;
import cz.kdolezal.taskmanagementsystem.api.request.UserAddRequest;
import cz.kdolezal.taskmanagementsystem.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>>getAll(){
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<User>getById(@PathVariable("id")long id){
        return ResponseEntity.ok().body(userService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> add(@RequestBody UserAddRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.add(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void>delete(@PathVariable("id")long id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
