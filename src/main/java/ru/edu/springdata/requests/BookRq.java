package ru.edu.springdata.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRq {

    private String title;
    private String language;
    private Long categoryId;
    private Boolean active;
    private Set<Long> authorId = new HashSet<>();
}
