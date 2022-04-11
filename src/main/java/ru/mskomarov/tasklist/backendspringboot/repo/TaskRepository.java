package ru.mskomarov.tasklist.backendspringboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mskomarov.tasklist.backendspringboot.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
