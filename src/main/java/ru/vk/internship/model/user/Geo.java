package ru.vk.internship.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Geo {
    private String lat;
    private String lng;
}
