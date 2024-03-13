package ru.vk.internship.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vk.internship.model.Role;
import ru.vk.internship.model.exception.RoleManagementException;
import ru.vk.internship.repository.RoleRepository;
import ru.vk.internship.service.RoleService;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Override
    public Role getRoleById(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role == null)
            throw new RoleManagementException("Role not found");
        return role;
    }

    @Override
    public ArrayList<Role> getAllRoles() {
        return new ArrayList<>(roleRepository.findAll());
    }

    @Override
    public Role createRole(Role role) {
        if (roleRepository.findByName(role.getName()) != null)
            throw new RoleManagementException("Role already exists");
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
