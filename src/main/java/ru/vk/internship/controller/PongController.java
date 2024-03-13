package ru.vk.internship.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ping")
@AllArgsConstructor
public class PongController {

    @GetMapping
    public String pong() {
        return "pong";
    }
}
