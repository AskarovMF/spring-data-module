package ru.edu.springdata.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.edu.springdata.dao.BookDao;
import ru.edu.springdata.model.Book;
import ru.edu.springdata.model.BookRq;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "books")
public class BookController {

    private final BookDao service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getBooks(@RequestParam(required = false) String category, @RequestParam(required = false) String language){
        return service.getAllBooks(category, language);
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBook(@PathVariable Long id) {
        return service.getBook(id);
    }

    @PatchMapping(path = "{id}/update")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateBook(@PathVariable Long id, @RequestBody BookRq book) {
        service.updateBook(id, book);
    }

    @PostMapping(path = "add-book")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(@RequestBody BookRq book){
        service.saveBook(book);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable Long id){
        service.deleteBook(id);
    }
}
