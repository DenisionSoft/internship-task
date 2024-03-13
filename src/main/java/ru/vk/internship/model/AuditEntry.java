package ru.vk.internship.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.vk.internship.config.Consts;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = Consts.AUDIT_ENTRY_TABLE)
public class AuditEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime timestamp;
    private String username;
    private String requestMethod;
    private String requestUri;
    private String requestQuery;
}
