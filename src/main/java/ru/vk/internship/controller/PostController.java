package ru.vk.internship.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vk.internship.audit.RecordAudit;
import ru.vk.internship.model.Comment;
import ru.vk.internship.model.Post;
import ru.vk.internship.service.PostService;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController extends BaseController {

    private final PostService postService;

    @RecordAudit
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @RecordAudit
    @GetMapping
    public ResponseEntity<ArrayList<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @RecordAudit
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.createPost(post));
    }

    @RecordAudit
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        return ResponseEntity.ok(postService.updatePost(id, post));
    }

    @RecordAudit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }

    @RecordAudit
    @GetMapping("/{id}/comments")
    public ResponseEntity<ArrayList<Comment>> getPostComments(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostComments(id));
    }
}
