package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Category")
@Table(name = "category")
public class Category {

    public Category() {
    }

    public Category(Long id, String name, String slug, String image, String status, Long showHome) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.image = image;
        this.status = status;
        this.showHome = showHome;
    }

    @Id
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "category_sequence"
    )
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    @Column(
            nullable = false,
            name = "name"
    )
    private String name;

    @NotBlank(message = "Slug is required")
    @Column(
            nullable = false,
            name = "slug"
    )
    private String slug;

    @Column(
            name = "image"
    )
    private String image;

    @NotBlank(message = "Status is required")
    @Column(
            nullable = false,
            name = "status"
    )
    private String status;

    @NotNull(message = "Show on home field is required")
    @Column(nullable = false, name = "show_home")
    private Long showHome;

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getShowHome() {
        return showHome;
    }

    public void setShowHome(Long showHome) {
        this.showHome = showHome;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                ", showHome=" + showHome +
                '}';
    }
}
