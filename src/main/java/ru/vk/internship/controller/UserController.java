package ru.vk.internship.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vk.internship.audit.RecordAudit;
import ru.vk.internship.model.Album;
import ru.vk.internship.model.Post;
import ru.vk.internship.model.Todo;
import ru.vk.internship.model.user.User;
import ru.vk.internship.service.UserService;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController extends BaseController {

    private final UserService userService;

    @RecordAudit
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @RecordAudit
    @GetMapping
    public ResponseEntity<ArrayList<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @RecordAudit
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @RecordAudit
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @RecordAudit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @RecordAudit
    @GetMapping("/{id}/albums")
    public ResponseEntity<ArrayList<Album>> getUserAlbums(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserAlbums(id));
    }

    @RecordAudit
    @GetMapping("/{id}/todos")
    public ResponseEntity<ArrayList<Todo>> getUserTodos(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserTodos(id));
    }

    @RecordAudit
    @GetMapping("/{id}/posts")
    public ResponseEntity<ArrayList<Post>> getUserPosts(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserPosts(id));
    }

}
