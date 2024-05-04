package ru.edu.springdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.springdata.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
