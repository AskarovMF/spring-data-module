package ru.edu.springdata.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookRq {

    private String name;
    private String language;
    private String category; // history, it, health etc...
}
