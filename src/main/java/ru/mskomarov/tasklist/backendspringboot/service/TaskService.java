package ru.mskomarov.tasklist.backendspringboot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mskomarov.tasklist.backendspringboot.entity.Task;
import ru.mskomarov.tasklist.backendspringboot.repo.TaskRepository;

import java.util.List;

@Service
@Transactional
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Task add(Task task) {
        return repository.save(task);
    }

    public Task update(Task task) {
        return repository.save(task);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Page findByParams(String title, Integer completed, Long priorityId, Long categoryId, PageRequest pageRequest) {
        return repository.findByParams(title, completed, priorityId, categoryId, pageRequest);
    }

    public Task findById(Long id) {
        return repository.findById(id).get();
    }
}
