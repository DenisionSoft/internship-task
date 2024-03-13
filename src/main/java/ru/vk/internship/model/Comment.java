package ru.vk.internship.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comment {
    private Long postId;
    private Long id;
    private String name;
    private String email;
    private String body;
}
