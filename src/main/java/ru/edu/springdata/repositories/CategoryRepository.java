package ru.edu.springdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.springdata.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String categoryName);
}
