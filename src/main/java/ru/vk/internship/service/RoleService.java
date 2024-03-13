package ru.vk.internship.service;

import ru.vk.internship.model.Role;

import java.util.ArrayList;

public interface RoleService {
    Role getRoleById(Long id);
    ArrayList<Role> getAllRoles();
    Role createRole(Role role);
    void deleteRole(Long id);
}
