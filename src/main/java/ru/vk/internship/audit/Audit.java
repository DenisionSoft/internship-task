package ru.vk.internship.audit;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.vk.internship.service.AuditService;

import java.time.LocalDateTime;

@Aspect
@Order(1)
@Component
@RequiredArgsConstructor
public class Audit {

    private final AuditService auditService;

    @Before("@annotation(RecordAudit)")
    public void recordAudit() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        LocalDateTime timestamp = LocalDateTime.now();
        String username = authentication.getName();
        String requestMethod = request.getMethod();
        String requestUri = request.getRequestURI();
        String requestQuery = request.getQueryString();
        auditService.createAuditEntry(timestamp, username, requestMethod, requestUri, requestQuery);
    }

}
