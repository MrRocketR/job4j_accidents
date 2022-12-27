package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Repository
public class AccidentRepositoryMeme {

    private final ConcurrentHashMap<Integer, Accident> store = new ConcurrentHashMap<>();
    private final AtomicInteger generatedIds = new AtomicInteger();
    private final Map<Integer, Rule> rulesMap = new HashMap<>();
    private final Map<Integer, AccidentType> typesMap = new HashMap<>();

    public AccidentRepositoryMeme() {
        rulesMap.put(1, new Rule(1, "Статья. 1"));
        rulesMap.put(2, new Rule(2, "Статья. 2"));
        rulesMap.put(3, new Rule(3, "Статья. 3"));
        typesMap.put(1, new AccidentType(1, "Две машины"));
        typesMap.put(2, new AccidentType(2, "Машина и человек"));
        typesMap.put(3, new AccidentType(3, "Машина и велосипед"));
    }

    public void add(Accident accident, String[] ids) {
        addTypesAndRules(accident, ids);
        accident.setId(generatedIds.incrementAndGet());
        store.put(accident.getId(), accident);
    }

    private void addTypesAndRules(Accident accident, String[] rulesIds) {
        accident.setType(typesMap.get(accident.getType().getId()));
        accident.setRules(new HashSet<>());
        Stream.of(rulesIds).map(Integer::valueOf)
                .forEach(i -> accident.getRules().add(rulesMap.get(i)));
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

    public Collection<Rule> getRules() {
        return rulesMap.values();
    }

    public Collection<AccidentType> getTypes() {
        return typesMap.values();
    }
}
