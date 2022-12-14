package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;
    @Column(name="author", updatable = false)
    private String author;
    @Column(name="isbn")
    private String isbn;
    @Column(name="print_year")
    @JsonProperty("print_year")
    private int printYear;
    @Column(name="read_already")
    @JsonProperty("read_already")
    private boolean readAlready;
}
