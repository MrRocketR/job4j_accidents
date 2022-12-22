package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AccidentService {

    private final AccidentRepository accidentRepository;

    public AccidentService(AccidentRepository accidentRepository) {
        this.accidentRepository = accidentRepository;
    }

    public void add(Accident accident) {
        accident.setType(getTypes().get(accident.getType().getId()));
        accidentRepository.add(accident);
    }
    public Collection<Accident> showAccidents() {
        Collection<Accident> accidents = accidentRepository.show();
        return accidents;
    }

    public Accident findById(int id) {
        return accidentRepository.findById(id);
    }
    public void update(Accident accident) {
        accidentRepository.update(accident);
    }
    public List<AccidentType> getTypes() {
        List<AccidentType> types = new ArrayList<>();
        types.add(new AccidentType(1, "Две машины"));
        types.add(new AccidentType(2, "Машина и человек"));
        types.add(new AccidentType(3, "Машина и велосипед"));
        return types;
    }
    public List<Rule> rules() {
        List<Rule> rules = List.of(
                new Rule(1, "Статья. 1"),
                new Rule(2, "Статья. 2"),
                new Rule(3, "Статья. 3")
        );
        return rules;
    }

}
