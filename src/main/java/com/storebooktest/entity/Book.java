package com.storebooktest.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
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
}
