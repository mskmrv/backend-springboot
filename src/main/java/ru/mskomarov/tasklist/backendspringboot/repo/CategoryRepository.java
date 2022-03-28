package ru.mskomarov.tasklist.backendspringboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mskomarov.tasklist.backendspringboot.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
