package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentRepository {

    private final ConcurrentHashMap<Integer, Accident> store = new ConcurrentHashMap<>();
    private final AtomicInteger generatedIds = new AtomicInteger();

    public void add(Accident accident) {
        accident.setId(generatedIds.incrementAndGet());
        store.put(accident.getId(), accident);
    }

    public Collection<Accident> show() {
        return store.values();
    }

    public Accident findById(int id) {
        return store.get(id);
    }

    public void update(Accident accident) {
        store.replace(accident.getId(), accident);

    }
}
