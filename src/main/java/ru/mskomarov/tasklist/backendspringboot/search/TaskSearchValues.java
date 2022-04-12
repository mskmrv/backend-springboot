package ru.mskomarov.tasklist.backendspringboot.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskSearchValues {
    private String title;
    private Integer completed;
    private Long priorityId;
    private Long categoryId;
}
