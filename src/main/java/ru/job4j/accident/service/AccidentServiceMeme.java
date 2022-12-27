package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepositoryMeme;

import java.util.*;
import java.util.stream.Stream;

@Service
public class AccidentServiceMeme {

    private final AccidentRepositoryMeme accidentRepositoryMeme;

    public AccidentServiceMeme(AccidentRepositoryMeme accidentRepositoryMeme) {
        this.accidentRepositoryMeme = accidentRepositoryMeme;
    }

    public void add(Accident accident, String[] ids) {
        accidentRepositoryMeme.add(accident, ids);
    }

    public Collection<Accident> showAccidents() {
        Collection<Accident> accidents = accidentRepositoryMeme.show();
        return accidents;
    }

    public Accident findById(int id) {
        return accidentRepositoryMeme.findById(id);
    }

    public void update(Accident accident) {
        accidentRepositoryMeme.update(accident);
    }

    public Collection<AccidentType> getTypes() {
        return accidentRepositoryMeme.getTypes();
    }

    public Collection<Rule> getRules() {
        return accidentRepositoryMeme.getRules();
    }

}
