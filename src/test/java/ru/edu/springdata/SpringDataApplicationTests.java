package ru.edu.springdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.edu.springdata.requests.BookRq;

import java.util.ArrayList;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringDataApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Получаем все книги")
    @Order(1)
    void shouldReturnAllBooks() throws Exception {
        this.mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(13)))
                .andExpect(jsonPath("$[*].title", hasItems("Война миров", "Автостопом по галактике", "Дюна",
                        "Первому игроку приготовиться", "Марсианин", "Одиссея 2001", "Охотник на ведьм", "Счастье кулинара",
                        "101 идея для десерта", "Дело ведет Самойлов", "Современная золушка", "Машина времени",
                        "Любовь вне времени")))
                .andExpect(jsonPath("$[*].category.name", hasItems("детектив", "кулинария", "роман",
                        "фантастика", "фэнтези")))
                .andExpect(jsonPath("$[*].authors[*].lastName", hasItems("Уэллс", "Адамс", "Герберт",
                        "Клайн", "вейер", "кларк", "Домбровский", "ХАНКИШИЕВ", "Дашкевич", "Васильев", "Мамлеева")))
                .andExpect(jsonPath("$[*].authors[*].address.city", hasItems("Лондон", "Санта-Барбара", "Сан Франциско",
                        "Эшленд", "Ричмонд", "Гемпшир", "Москва", "Ереван", "Санкт-Петербург", "Москва")));
    }

    @Test
    @Order(2)
    @DisplayName("Получаем книги только на английском языке")
    void shouldReturnFrenchBooks() throws Exception {
        this.mockMvc.perform(get("/books?language=английский"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(7)))
                .andExpect(jsonPath("$[*].title", hasItems("Война миров", "Автостопом по галактике", "Дюна",
                        "Первому игроку приготовиться", "Марсианин", "Одиссея 2001", "Машина времени")));
    }

    @Test
    @Order(3)
    @DisplayName("Получаем книги только в категории 'кулинария'")
    void shouldReturnPoetryBooks() throws Exception {
        this.mockMvc.perform(get("/books?category=кулинария"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$[*].title", hasItems("Счастье кулинара", "101 идея для десерта")));
    }

    @Test
    @Order(4)
    @DisplayName("Получаем книги только в категории 'кулинария' и только на русском языке")
    void shouldReturnRusRomanBooks() throws Exception {
        this.mockMvc.perform(get("/books?language=русский&category=роман"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$[*].title", hasItems("Современная золушка", "Любовь вне времени")));
    }

    @Test
    @Order(5)
    @DisplayName("Получем книгу по индексу")
    void shouldReturnOneBook() throws Exception {
        this.mockMvc.perform(get("/books/6"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is("Одиссея 2001")))
                .andExpect(jsonPath("$.language", is("английский")))
                .andExpect(jsonPath("$.category.name", is("фантастика")))
                .andExpect(jsonPath("$.authors[*].firstName", hasItem("Артур")))
                .andExpect(jsonPath("$.authors[*].lastName", hasItem("кларк")))
                .andExpect(jsonPath("$.authors[*].phone", hasItem("+1805199172")))
                .andExpect(jsonPath("$.authors[*].address.city", hasItem("Гемпшир")))
                .andExpect(jsonPath("$.authors[*].address.street", hasItem("Эвергрин")));
    }

    @Test
    @Order(6)
    @DisplayName("Добавляем новую книгу")
    void shouldAddOneBook() throws Exception {
        BookRq book = new BookRq("Лунная пыль", "русский", 1L, true, Set.of(6L, 1L));

        this.mockMvc.perform(post("/books/add-book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(book)))
                .andExpect(status().isCreated());

        this.mockMvc.perform(get("/books/14"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.title", is("Лунная пыль")))
                .andExpect(jsonPath("$.authors[*].lastName", hasItems("кларк", "Уэллс")));
    }

    @Test
    @Order(7)
    @DisplayName("Обновляем данные о книге")
    void shouldChangeOneBook() throws Exception {

        BookRq bookRq = new BookRq("Любовь вне времени и пространства",
                "русский",
                1L,
                false,
                Set.of(6L, 1L));

        this.mockMvc.perform(patch("/books/13/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(bookRq)))
                .andExpect(status().isCreated());

        this.mockMvc.perform(get("/books/13"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Любовь вне времени и пространства")))
                .andExpect(jsonPath("$.category.name", is("фантастика")))
                .andExpect(jsonPath("$.authors[*].lastName", hasItems("кларк", "Уэллс")));
    }

    @Test
    @Order(8)
    @DisplayName("Удаляем книгу")
    void shouldDeleteOneBook() throws Exception {
        this.mockMvc.perform(delete("/books/13"))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/books/13"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}
