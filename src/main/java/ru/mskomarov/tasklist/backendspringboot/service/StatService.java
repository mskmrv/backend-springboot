package ru.mskomarov.tasklist.backendspringboot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mskomarov.tasklist.backendspringboot.entity.Stat;
import ru.mskomarov.tasklist.backendspringboot.repo.StatRepository;

@Service
@Transactional
public class StatService {
    private final StatRepository repository;

    public StatService(StatRepository repository) {
        this.repository = repository;
    }

    public Stat findById(Long id) {
        return repository.findById(id).get();
    }
}
