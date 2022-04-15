package ru.mskomarov.tasklist.backendspringboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mskomarov.tasklist.backendspringboot.entity.Category;
import ru.mskomarov.tasklist.backendspringboot.search.CategorySearchValues;
import ru.mskomarov.tasklist.backendspringboot.service.CategoryService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public List<Category> findAll() {
        logger.info("\n-------------------------------");
        logger.info("\nCategoryController: findAll()");

        return categoryService.findAllByOrderByTitleAsc();
    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {
        logger.info("\n-------------------------------");
        logger.info("\nCategoryController: add()");

        if (category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryService.add(category));
    }

    @PutMapping("/update")
    public ResponseEntity<Category> update(@RequestBody Category category) {
        logger.info("\n-------------------------------");
        logger.info("\nCategoryController: update()");

        if (category.getId() == null || category.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        categoryService.update(category);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        logger.info("\n-------------------------------");
        logger.info("\nCategoryController: findById()");

        Category category = null;

        try {
            category = categoryService.findById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        logger.info("\n-------------------------------");
        logger.info("\nCategoryController: delete()");

        try {
            categoryService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchValues categorySearchValues) {
        logger.info("\n-------------------------------");
        logger.info("\nCategoryController: search()");

        return ResponseEntity.ok(categoryService.findByTitle(categorySearchValues.getText()));
    }
}
