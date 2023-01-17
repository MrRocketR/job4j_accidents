package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepositoryMeme;

import java.util.*;

@Service
public class AccidentServiceMeme {

    private final AccidentRepositoryMeme store;

    public AccidentServiceMeme(AccidentRepositoryMeme accidentRepositoryMeme) {
        this.store = accidentRepositoryMeme;
    }

    public void add(Accident accident, String[] ids) {
        store.add(accident, ids);
    }

    public Collection<Accident> showAccidents() {
        Collection<Accident> accidents = store.show();
        return accidents;
    }

    public Accident findById(int id) {
        return store.findById(id);
    }

    public void update(Accident accident) {
        store.update(accident);
    }

    public Collection<AccidentType> getTypes() {
        return store.getTypes();
    }

    public Collection<Rule> getRules() {
        return store.getRules();
    }

}
