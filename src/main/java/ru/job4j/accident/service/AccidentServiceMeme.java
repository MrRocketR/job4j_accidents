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
    private final List<AccidentType> types = new ArrayList<>();

    public AccidentServiceMeme(AccidentRepositoryMeme accidentRepositoryMeme) {
        this.accidentRepositoryMeme = accidentRepositoryMeme;
        types.add(new AccidentType(1, "Две машины"));
        types.add(new AccidentType(2, "Машина и человек"));
        types.add(new AccidentType(3, "Машина и велосипед"));
    }

    public void add(Accident accident) {
        List<AccidentType> types = getTypes();
        AccidentType type = types.stream()
                .filter(at -> at.getId() == accident.getType()
                        .getId()).findFirst().get();
        accident.setType(type);
        accidentRepositoryMeme.add(accident);
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

    public List<AccidentType> getTypes() {
        return types;
    }

    public Map<Integer, Rule> getRules() {
        return accidentRepositoryMeme.getRules();
    }


    public void fillRules(Accident accident, String[] rls) {
        accident.setRules(new HashSet<>());
        Stream.of(rls).map(Integer::valueOf)
                .forEach(i -> accident.getRules().add(getRules().get(i)));
    }

}
