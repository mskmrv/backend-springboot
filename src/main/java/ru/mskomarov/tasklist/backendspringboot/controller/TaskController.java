package ru.mskomarov.tasklist.backendspringboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mskomarov.tasklist.backendspringboot.entity.Task;
import ru.mskomarov.tasklist.backendspringboot.search.TaskSearchValues;
import ru.mskomarov.tasklist.backendspringboot.service.TaskService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/task")
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAll() {
        logger.info("\n-------------------------------");
        logger.info("\nTaskController: findAll()");

        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {
        logger.info("\n-------------------------------");
        logger.info("\nTaskController: findById()");

        Task task = null;
        try {
            task = taskService.findById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(task);
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

        return ResponseEntity.ok(taskService.add(task));
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

        taskService.update(task);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Task> delete(@PathVariable Long id) {
        logger.info("\n-------------------------------");
        logger.info("\nTaskController: delete()");

        try {
            taskService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<Task>> search(@RequestBody TaskSearchValues taskSearchValues) {
        logger.info("\n-------------------------------");
        logger.info("\nTaskController: search()");

        String title = taskSearchValues.getTitle();
        Integer completed = taskSearchValues.getCompleted();
        Long priorityId = taskSearchValues.getPriorityId();
        Long categoryId = taskSearchValues.getCategoryId();
        String sortColumn = taskSearchValues.getSortColumn();
        String sortDirection = taskSearchValues.getSortDirection();

        Integer pageNumber = taskSearchValues.getPageNumber();
        Integer pageSize = taskSearchValues.getPageSize();

        Sort.Direction direction = sortDirection == null || sortDirection.trim().length() == 0 || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortColumn);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        Page result = taskService.findByParams(title, completed, priorityId, categoryId, pageRequest);

        return ResponseEntity.ok(result);
    }
}
