package ru.vk.internship.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.vk.internship.config.Consts;
import ru.vk.internship.model.Album;
import ru.vk.internship.model.Post;
import ru.vk.internship.model.Todo;
import ru.vk.internship.model.user.User;
import ru.vk.internship.proxy.ProxyClient;
import ru.vk.internship.service.UserService;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final ProxyClient proxyClient;

    @Override
    @Cacheable(value = "user", key = "#id")
    public User getUserById(Long id) {
        return proxyClient.get(Consts.URI_USERS + "/" + id, User.class);
    }

    @Override
    @Cacheable(value = "users")
    public ArrayList<User> getAllUsers() {
        return proxyClient.get(Consts.URI_USERS, ArrayList.class);
    }

    @Override
    public User createUser(User user) {
        return proxyClient.post(Consts.URI_USERS, user, User.class);
    }

    @Override
    @CachePut(value = "user", key = "#id")
    public User updateUser(Long id, User user) {
        return proxyClient.put(Consts.URI_USERS + "/" + id, user, User.class);
    }

    @Override
    @CacheEvict(value = "user", key = "#id")
    public void deleteUser(Long id) {
        proxyClient.delete(Consts.URI_USERS + "/" + id);
    }

    @Override
    @Cacheable(value = "userAlbums", key = "#id")
    public ArrayList<Album> getUserAlbums(Long id) {
        return proxyClient.get(Consts.URI_USERS + "/" + id + Consts.URI_ALBUMS, ArrayList.class);
    }

    @Override
    @Cacheable(value = "userTodos", key = "#id")
    public ArrayList<Todo> getUserTodos(Long id) {
        return proxyClient.get(Consts.URI_USERS + "/" + id + Consts.URI_TODOS, ArrayList.class);
    }

    @Override
    @Cacheable(value = "userPosts", key = "#id")
    public ArrayList<Post> getUserPosts(Long id) {
        return proxyClient.get(Consts.URI_USERS + "/" + id + Consts.URI_POSTS, ArrayList.class);
    }
}
