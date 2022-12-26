package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentRepositoryMeme {

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

    public Map<Integer, Rule> getRules() {
        Map<Integer, Rule> map = new HashMap<>();
        map.put(1, new Rule(1, "Статья. 1"));
        map.put(2, new Rule(2, "Статья. 2"));
        map.put(3, new Rule(3, "Статья. 3"));
        return map;
    }
}
