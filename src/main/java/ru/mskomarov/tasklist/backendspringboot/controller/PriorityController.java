package ru.mskomarov.tasklist.backendspringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mskomarov.tasklist.backendspringboot.entity.Priority;
import ru.mskomarov.tasklist.backendspringboot.repo.PriorityRepository;

import java.util.List;

@RestController
@RequestMapping("/priority")
public class PriorityController {
    private PriorityRepository priorityRepository;

    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @GetMapping("/all")
    public List<Priority> getPriorityList() {
        return priorityRepository.findAll();
    }
}
