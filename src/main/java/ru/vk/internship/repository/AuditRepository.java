package ru.vk.internship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vk.internship.model.AuditEntry;

public interface AuditRepository extends JpaRepository<AuditEntry, Long> {
}
