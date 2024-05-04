package ru.edu.springdata.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.edu.springdata.DTO.BookDTO;
import ru.edu.springdata.model.Author;
import ru.edu.springdata.model.Book;
import ru.edu.springdata.model.Category;
import ru.edu.springdata.repositories.AuthorRepository;
import ru.edu.springdata.repositories.BookRepository;
import ru.edu.springdata.repositories.CategoryRepository;
import ru.edu.springdata.requests.BookRq;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public List<BookDTO> getAllBooks(String categoryName, String language) {
        List<Book> books;

        if (!StringUtils.hasText(categoryName) && !StringUtils.hasText(language)) {
            books = bookRepository.findAll();
        } else if (StringUtils.hasText(categoryName) && !StringUtils.hasText(language)) {
            Category category = categoryRepository.findByName(categoryName);
            books = bookRepository.findBookByCategory(category);
        } else if (!StringUtils.hasText(categoryName) && StringUtils.hasText(language)) {
            books = bookRepository.findBookByLanguage(language);
        } else {
            Category category = categoryRepository.findByName(categoryName);
            books = bookRepository.findBookByCategoryAndLanguage(category, language);
        }

        Type listType = new TypeToken<List<BookDTO>>() {}.getType();

        return modelMapper.map(books, listType);
    }

    public BookDTO getBook(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Type bookType = new TypeToken<BookDTO>() {}.getType();
            return modelMapper.map(bookOptional.get(), bookType);
        }
        return null;
    }

    @Transactional
    public void saveBook(BookRq bookRq) {
        Book book = new Book();
        book.setTitle(bookRq.getTitle());
        book.setLanguage(bookRq.getLanguage());
        book.setActive(bookRq.getActive());

        categoryRepository.findById(bookRq.getCategoryId()).ifPresent(book::setCategory);

        Set<Author> authors = bookRq.getAuthorId().stream().map(authorRepository::findById).filter(Optional::isPresent)
                .map(Optional::get).collect(Collectors.toSet());
        book.setAuthors(authors);

        bookRepository.save(book);
    }

    @Transactional
    public void updateBook(Long id, BookRq bookRq) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isEmpty()) {
            return;
        }

        Book book = optionalBook.get();

        if (bookRq.getTitle() != null) {
            book.setTitle(bookRq.getTitle());
        }

        if (bookRq.getLanguage() != null) {
            book.setLanguage(bookRq.getLanguage());
        }

        if (bookRq.getActive() != null) {
            book.setActive(bookRq.getActive());
        }

        if (bookRq.getCategoryId() != null) {
            categoryRepository.findById(bookRq.getCategoryId()).ifPresent(book::setCategory);
        }

        if (!bookRq.getAuthorId().isEmpty()) {
            List<Author> authorsList = authorRepository.findAllById(bookRq.getAuthorId());
            book.setAuthors(new HashSet<>(authorsList));
        }

        bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
