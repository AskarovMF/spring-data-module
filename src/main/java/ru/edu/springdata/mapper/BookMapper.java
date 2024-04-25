package ru.edu.springdata.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.edu.springdata.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setName(rs.getString("name"));
        book.setLanguage(rs.getString("language"));
        book.setCategory(rs.getString("category"));

        return book;
    }
}
