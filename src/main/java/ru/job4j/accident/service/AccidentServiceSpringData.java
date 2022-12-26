package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepositorySpringData;

@Service
@AllArgsConstructor
public class AccidentServiceSpringData {
    private final AccidentRepositorySpringData store;

    public void create(Accident accident) {
        store.save(accident);
    }

    public Iterable<Accident> getAll() {
        return store.findAll();
    }
}
