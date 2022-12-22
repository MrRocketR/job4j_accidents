package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentRepository {

    private  ConcurrentHashMap<Integer, Accident> store = new ConcurrentHashMap<>();
    private  static AtomicInteger generatedIds = new AtomicInteger(1);

    public void add(Accident accident) {
        while (store.containsKey(generatedIds.get())) {
            generatedIds.incrementAndGet();
        }
        accident.setId(generatedIds.get());
        store.put(generatedIds.get(), accident);
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
