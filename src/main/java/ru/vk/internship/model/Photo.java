package ru.vk.internship.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Photo {
    private Long albumId;
    private Long id;
    private String title;
    private String url;
    private String thumbnailUrl;
}
