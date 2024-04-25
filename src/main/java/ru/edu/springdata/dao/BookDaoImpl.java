package ru.edu.springdata.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import ru.edu.springdata.mapper.BookMapper;
import ru.edu.springdata.model.Book;
import ru.edu.springdata.model.BookRq;

import java.util.List;

@AllArgsConstructor
@Repository
public class BookDaoImpl implements BookDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final String CATEGORY = "category";
    private static final String LANGUAGE = "language";

    @Override
    public List<Book> getAllBooks(String category, String language) {
        String sql;
        MapSqlParameterSource source = new MapSqlParameterSource();

        if (StringUtils.isEmpty(category) && StringUtils.isEmpty(language)) {
            sql = "select * from books";
        } else if (!StringUtils.isEmpty(category) && StringUtils.isEmpty(language)) {
            sql = "select * from BOOKS where category = :category";
            source.addValue(CATEGORY, category);
        } else if (StringUtils.isEmpty(category) && !StringUtils.isEmpty(language)) {
            sql = "select * from books where language = :language";
            source.addValue(LANGUAGE, language);
        } else {
            sql = "select * from books where category = :category and language = :language";
            source.addValue(LANGUAGE, language);
            source.addValue(CATEGORY, category);
        }
        return jdbcTemplate.query(sql, source, new BookMapper());
    }

    @Override
    public void saveBook(BookRq book) {
        MapSqlParameterSource source = new MapSqlParameterSource("name", book.getName());
        source.addValue("language", book.getLanguage());
        source.addValue("category", book.getCategory());

        jdbcTemplate.update("insert into books(name, language, category) values(:name, :language, :category)", source);
    }

    @Override
    public void updateBook(Long id, BookRq book) {
        MapSqlParameterSource source = new MapSqlParameterSource("name", book.getName());
        source.addValue("language", book.getLanguage());
        source.addValue("category", book.getCategory());
        source.addValue("id", id);

        jdbcTemplate.update("update books set name = :name, language = :language, category = :category where id =:id", source);
    }

    @Override
    public void deleteBook(Long id) {
        MapSqlParameterSource source = new MapSqlParameterSource("id", id);
        jdbcTemplate.update("delete from books where id = :id", source);
    }

    @Override
    public Book getBook(Long id) {
        SqlParameterSource source = new MapSqlParameterSource("id", id);
        List<Book> books = jdbcTemplate.query("select * from books where id = :id",
                source,
                new BeanPropertyRowMapper<>(Book.class));

        return books.stream().findFirst().orElse(null);
    }
}
