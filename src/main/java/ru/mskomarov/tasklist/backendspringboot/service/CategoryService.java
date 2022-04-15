package ru.mskomarov.tasklist.backendspringboot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mskomarov.tasklist.backendspringboot.entity.Category;
import ru.mskomarov.tasklist.backendspringboot.repo.CategoryRepository;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category add(Category category) {
        return repository.save(category);
    }

    public Category update(Category category) {
        return repository.save(category);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Category> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public Category findById(Long id) {
        return repository.findById(id).get();
    }

    public List<Category> findAllByOrderByTitleAsc() {
        return repository.findAllByOrderByTitleAsc();
    }
}
