package ru.vk.internship.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vk.internship.audit.RecordAudit;
import ru.vk.internship.model.Role;
import ru.vk.internship.service.RoleService;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/roles")
@AllArgsConstructor
public class RoleController extends BaseController {

    private final RoleService roleService;

    @RecordAudit
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @RecordAudit
    @GetMapping
    public ResponseEntity<ArrayList<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @RecordAudit
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.createRole(role));
    }

    @RecordAudit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok().build();
    }
}
