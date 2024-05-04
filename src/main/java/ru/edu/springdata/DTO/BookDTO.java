package ru.edu.springdata.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class BookDTO {

    private Long id;
    private String title;
    private String language;
    private CategoryDTO category;
    private Boolean active;
    private Set<AuthorDTO> authors = new HashSet<>();
}
