package ru.vk.internship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vk.internship.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
