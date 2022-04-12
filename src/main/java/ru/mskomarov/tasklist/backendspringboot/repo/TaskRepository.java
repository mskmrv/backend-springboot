package ru.mskomarov.tasklist.backendspringboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.mskomarov.tasklist.backendspringboot.entity.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT p FROM Task p where " +
            "(:title is null or :title='' or lower(p.title) like lower(concat('%', :title, '%'))) and" +
            "(:completed is null or p.completed=:completed) and" +
            "(:priorityId is null or p.priority.id=:priorityId) and" +
            "(:categoryId is null or p.category.id=:categoryId)"
    )
    List<Task> findByParams(@Param("title") String title,
                            @Param("completed") Integer completed,
                            @Param("priorityId") Long priorityId,
                            @Param("categoryId") Long categoryId);
}
