package ru.vk.internship.service;

import java.time.LocalDateTime;

public interface AuditService {
    void createAuditEntry(LocalDateTime timestamp, String username, String requestMethod, String requestUri, String requestQuery);
}
