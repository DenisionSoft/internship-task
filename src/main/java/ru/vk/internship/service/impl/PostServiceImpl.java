package ru.vk.internship.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.vk.internship.config.Consts;
import ru.vk.internship.model.Comment;
import ru.vk.internship.model.Post;
import ru.vk.internship.proxy.ProxyClient;
import ru.vk.internship.service.PostService;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final ProxyClient proxyClient;

    @Override
    @Cacheable(value = "post", key = "#id")
    public Post getPostById(Long id) {
        return proxyClient.get(Consts.URI_POSTS + "/" + id, Post.class);
    }

    @Override
    @Cacheable(value = "posts")
    public ArrayList<Post> getAllPosts() {
        return proxyClient.get(Consts.URI_POSTS, ArrayList.class);
    }

    @Override
    public Post createPost(Post post) {
        return proxyClient.post(Consts.URI_POSTS, post, Post.class);
    }

    @Override
    @CachePut(value = "post", key = "#id")
    public Post updatePost(Long id, Post post) {
        return proxyClient.put(Consts.URI_POSTS + "/" + id, post, Post.class);
    }

    @Override
    @CacheEvict(value = "post", key = "#id")
    public void deletePost(Long id) {
        proxyClient.delete(Consts.URI_POSTS + "/" + id);
    }

    @Override
    @Cacheable(value = "postComments", key = "#id")
    public ArrayList<Comment> getPostComments(Long id) {
        return proxyClient.get(Consts.URI_POSTS + "/" + id + Consts.URI_COMMENTS, ArrayList.class);
    }
}
