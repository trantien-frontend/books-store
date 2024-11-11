package com.storebooktest.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "Books")
public class Book {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    String author;
    @Column(name = "published_date", nullable = false)
    LocalDate publishedDate;
    @Column(unique = true, nullable = false)
    String isbn;
    @Column(nullable = false)
    BigDecimal price;
    @Column(name = "created_at", updatable = false)
    LocalDateTime createAt;
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        if (createAt == null) this.createAt = LocalDateTime.now();
        if (updatedAt == null) this.updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
