package ru.edu.springdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.springdata.model.Book;
import ru.edu.springdata.model.Category;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBookByCategory(Category category);

    List<Book> findBookByLanguage(String language);

    List<Book> findBookByCategoryAndLanguage(Category category, String language);
}
