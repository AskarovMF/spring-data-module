package ru.edu.springdata.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.edu.springdata.DTO.BookDTO;
import ru.edu.springdata.requests.BookRq;
import ru.edu.springdata.services.BookService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getBooks(@RequestParam(required = false) String category, @RequestParam(required = false) String language){
        return bookService.getAllBooks(category, language);
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @PatchMapping(path = "{id}/update")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateBook(@PathVariable Long id, @RequestBody BookRq bookRq) {
        bookService.updateBook(id, bookRq);
    }

    @PostMapping(path = "add-book")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(@RequestBody BookRq book){
        bookService.saveBook(book);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }
}
