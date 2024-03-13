package ru.vk.internship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vk.internship.model.Album;
import ru.vk.internship.model.user.User;
import ru.vk.internship.proxy.ProxyClient;
import ru.vk.internship.service.UserService;
import ru.vk.internship.service.impl.UserServiceImpl;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
    private UserService userService;

    @Mock
    private ProxyClient proxyClient;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(proxyClient);
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        when(proxyClient.get(anyString(), eq(User.class))).thenReturn(user);
        assert userService.getUserById(1L).equals(user);
    }

    @Test
    public void testGetAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        when(proxyClient.get(anyString(), eq(ArrayList.class))).thenReturn(users);
        assert userService.getAllUsers().equals(users);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        when(proxyClient.post(anyString(), eq(user), eq(User.class))).thenReturn(user);
        assert userService.createUser(user).equals(user);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        when(proxyClient.put(anyString(), eq(user), eq(User.class))).thenReturn(user);
        assert userService.updateUser(1L, user).equals(user);
    }

    @Test
    public void testGetUserAlbums() {
        ArrayList<Album> albums = new ArrayList<>();
        albums.add(new Album());
        albums.add(new Album());
        when(proxyClient.get(anyString(), eq(ArrayList.class))).thenReturn(albums);
        assert userService.getUserAlbums(1L).equals(albums);
    }
}
