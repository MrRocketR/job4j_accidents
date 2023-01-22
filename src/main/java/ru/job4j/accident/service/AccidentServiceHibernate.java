package ru.job4j.accident.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.List;

@Service
@AllArgsConstructor
public class AccidentServiceHibernate {
    private final AccidentHibernate store;

    public List<Rule> getRules() {
        return store.getRules();
    }

    public List<AccidentType> getTypes() {
        return store.getTypes();
    }

    public List<Accident> showAccidents() {
        return store.showAll();
    }

    public Accident findById(int id) {
        return store.findById(id);
    }

    public void add(Accident accident, String[] ids) {
        store.save(accident, ids);
    }

    public void update(Accident accident, String[] ids) {
        store.update(accident, ids);
    }
}
