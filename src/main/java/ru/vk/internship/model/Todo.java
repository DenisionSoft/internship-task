package ru.vk.internship.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Todo {
    private Long userId;
    private Long id;
    private String title;
    private Boolean completed;
}
