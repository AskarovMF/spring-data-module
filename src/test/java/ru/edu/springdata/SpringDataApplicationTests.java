package ru.edu.springdata;

import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.edu.springdata.model.Book;
import ru.edu.springdata.model.BookRq;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringDataApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void shouldReturnAllBooks() throws Exception {
        this.mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(10)))
                .andExpect(jsonPath("$[*].name", hasItems("Война и мир", "Отверженные", "Гроздья гнева")));
    }

    @Test
    @Order(2)
    void shouldReturnFrenchBooks() throws Exception {
        this.mockMvc.perform(get("/books?language=fr"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[*].name", hasItems("Отверженные")));
    }

    @Test
    @Order(3)
    void shouldReturnPoetryBooks() throws Exception {
        this.mockMvc.perform(get("/books?category=поэзия"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[*].name", hasItems("Руслан и Людмила")));
    }

    @Test
    @Order(4)
    void shouldReturnRusRomanBooks() throws Exception {
        this.mockMvc.perform(get("/books?language=rus&category=роман"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[*].name", hasItems("Война и мир")));
    }

    @Test
    @Order(5)
    void shouldReturnOneBook() throws Exception {
        this.mockMvc.perform(get("/books/6"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Дон Кихот")))
                .andExpect(jsonPath("$.language", is("ru")))
                .andExpect(jsonPath("$.category", is("повесть")));
    }

    @Test
    @Order(6)
    void shouldChangeOneBook() throws Exception {
        Book book = new Book(6L, "Дон Кихот", "esp", "роман");

        this.mockMvc.perform(patch("/books/6/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(book)))
                .andExpect(status().isCreated());

        this.mockMvc.perform(get("/books/6"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Дон Кихот")))
                .andExpect(jsonPath("$.language", is("esp")))
                .andExpect(jsonPath("$.category", is("роман")));
    }

    @Test
    @Order(7)
    void shouldAddOneBook() throws Exception {
        BookRq book = new BookRq("Три сестры", "rus", "роман");

        this.mockMvc.perform(post("/books/add-book")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(book)))
                .andExpect(status().isCreated());

        this.mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(11)));
    }

    @Test
    @Order(8)
    void shouldDeleteOneBook() throws Exception {
        this.mockMvc.perform(delete("/books/6"))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(10)));
    }
}
