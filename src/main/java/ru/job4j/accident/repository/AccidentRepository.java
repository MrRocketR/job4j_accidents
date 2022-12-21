package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentRepository {

    private ConcurrentHashMap<Integer, Accident> store = new ConcurrentHashMap<>();

    public void add(Accident accident) {
        while (store.containsKey(accident.getId())) {
            accident.setId(accident.getId() + 1);
        }
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
