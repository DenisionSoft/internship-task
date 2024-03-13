package ru.vk.internship.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vk.internship.model.AuditEntry;
import ru.vk.internship.repository.AuditRepository;
import ru.vk.internship.service.AuditService;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;
    @Override
    public void createAuditEntry(LocalDateTime timestamp, String username, String requestMethod, String requestUri, String requestQuery){
        AuditEntry auditEntry = new AuditEntry();
        auditEntry.setTimestamp(timestamp);
        auditEntry.setUsername(username);
        auditEntry.setRequestMethod(requestMethod);
        auditEntry.setRequestUri(requestUri);
        auditEntry.setRequestQuery(requestQuery);
        auditRepository.save(auditEntry);
    }
}
