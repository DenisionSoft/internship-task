package ru.vk.internship.service;

import ru.vk.internship.model.Album;
import ru.vk.internship.model.Post;
import ru.vk.internship.model.Todo;
import ru.vk.internship.model.user.User;

import java.util.ArrayList;

public interface UserService {
    User getUserById(Long id);
    ArrayList<User> getAllUsers();
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);

    ArrayList<Album> getUserAlbums(Long id);
    ArrayList<Todo> getUserTodos(Long id);
    ArrayList<Post> getUserPosts(Long id);


}
