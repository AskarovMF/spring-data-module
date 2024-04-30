package ru.edu.springdata.dao;

import org.springframework.web.bind.annotation.RequestParam;
import ru.edu.springdata.model.Book;
import ru.edu.springdata.model.BookRq;

import java.util.List;

public interface BookDao {

    List<Book> getAllBooks(String category, String language);

    void saveBook(BookRq book);

    void updateBook(Long id, BookRq book);

    void deleteBook(Long id);

    Book getBook(Long id);
}
