package ru.mskomarov.tasklist.backendspringboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mskomarov.tasklist.backendspringboot.entity.Stat;
import ru.mskomarov.tasklist.backendspringboot.repo.StatRepository;

@RestController
public class StatController {
    private StatRepository statRepository;
    private final Long defaultId = 1L;

    public StatController(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    @GetMapping("/stat")
    public ResponseEntity<Stat> findById() {
        return ResponseEntity.ok(statRepository.findById(defaultId).get());
    }
}
