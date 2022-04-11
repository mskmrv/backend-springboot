package ru.mskomarov.tasklist.backendspringboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mskomarov.tasklist.backendspringboot.entity.Task;
import ru.mskomarov.tasklist.backendspringboot.repo.TaskRepository;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository, ConfigurableEnvironment environment) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAll() {
        logger.info("\n-------------------------------");
        logger.info("\nTaskController: findAll()");

        return ResponseEntity.ok(taskRepository.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {
        logger.info("\n-------------------------------");
        logger.info("\nTaskController: findById()");

        return ResponseEntity.ok(taskRepository.findById(id).get());
    }

    @PostMapping("/add")
    public ResponseEntity<Task> add(@RequestBody Task task) {
        logger.info("\n-------------------------------");
        logger.info("\nTaskController: add()");

        if (task.getId() != null && task.getId() != 0) {
            return new ResponseEntity("redundant param: id Must bs null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param:title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(taskRepository.save(task));
    }

    @PutMapping("/update")
    public ResponseEntity<Task> update(@RequestBody Task task) {
        logger.info("\n-------------------------------");
        logger.info("\nTaskController: update()");

        if (task.getId() == null || task.getId() == 0) {
            return new ResponseEntity("missed paarm: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        taskRepository.save(task);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Task> delete(@PathVariable Long id) {
        logger.info("\n-------------------------------");
        logger.info("\nTaskController: delete()");

        try {
            taskRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
