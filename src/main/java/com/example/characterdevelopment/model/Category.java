package com.example.characterdevelopment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "category")
@NoArgsConstructor @Getter @Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int level;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "hierarchy_level")
    private int hierarchyLevel;

    @OneToMany(mappedBy = "category")
    private List<Task> tasks;
}
