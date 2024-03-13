package ru.vk.internship.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.vk.internship.model.exception.AccountManagementException;
import ru.vk.internship.model.exception.RoleManagementException;
import ru.vk.internship.model.exception.ServiceFailureException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class BaseController {
    @ExceptionHandler(AccountManagementException.class)
    void handleAccountNotFoundException(HttpServletResponse response, Exception exception) throws IOException {
        sendResponse(response, HttpServletResponse.SC_NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(RoleManagementException.class)
    void handleRoleNotFoundException(HttpServletResponse response, Exception exception) throws IOException {
        sendResponse(response, HttpServletResponse.SC_NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(ServiceFailureException.class)
    void handleServiceFailureException(HttpServletResponse response, Exception exception) throws IOException {
        sendResponse(response, HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }

    void sendResponse(HttpServletResponse response, int status, String errorMsg) throws IOException {
        response.setStatus(status);
        ObjectMapper mapper = new ObjectMapper();
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()))) {
            bw.write(mapper.writeValueAsString(errorMsg));
        }
    }
}
