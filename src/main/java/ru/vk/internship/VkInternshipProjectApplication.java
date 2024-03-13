package ru.vk.internship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class VkInternshipProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(VkInternshipProjectApplication.class, args);
    }

}
