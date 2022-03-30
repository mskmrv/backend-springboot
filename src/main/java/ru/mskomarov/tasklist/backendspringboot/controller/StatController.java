package ru.mskomarov.tasklist.backendspringboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mskomarov.tasklist.backendspringboot.entity.Stat;
import ru.mskomarov.tasklist.backendspringboot.repo.StatRepository;

@RestController
public class StatController {
    private static final Logger logger = LoggerFactory.getLogger(StatController.class);
    private final Long defaultId = 1L;
    private StatRepository statRepository;

    public StatController(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    @GetMapping("/stat")
    public ResponseEntity<Stat> findById() {
        logger.info("\n-------------------------------");
        logger.info("\nStatController: findById()");

        return ResponseEntity.ok(statRepository.findById(defaultId).get());
    }
}
