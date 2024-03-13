package ru.vk.internship.service;

import ru.vk.internship.model.Comment;
import ru.vk.internship.model.Post;

import java.util.ArrayList;

public interface PostService {
    Post getPostById(Long id);
    ArrayList<Post> getAllPosts();
    Post createPost(Post post);
    Post updatePost(Long id, Post post);
    void deletePost(Long id);

    ArrayList<Comment> getPostComments(Long id);
}
