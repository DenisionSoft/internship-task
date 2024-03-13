package ru.vk.internship.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.vk.internship.config.Consts;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = Consts.ROLE_TABLE)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
}
