package com.example.demo.models;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "TempImage")
@Table(name = "temp_image")
public class TempImage {

    @Id
    @SequenceGenerator(
            name = "temp_image_sequence",
            sequenceName = "temp_image_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "temp_image_sequence"
    )
    private Long id;

    @Column(
            nullable = false,
            name = "name"
    )
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
