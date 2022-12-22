package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.*;
import java.util.stream.Stream;

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
    public Map<Integer, AccidentType> getTypes() {
        Map<Integer, AccidentType> map = new HashMap<>();
        map.put(1, new AccidentType(1, "Две машины"));
        map.put(2, new AccidentType(2, "Машина и человек"));
        map.put(3, new AccidentType(3, "Машина и велосипед"));
        return map;
    }
    public List<Rule> getRules() {
        List<Rule> rules = List.of(
                new Rule(1, "Статья. 1"),
                new Rule(2, "Статья. 2"),
                new Rule(3, "Статья. 3")
        );
        return rules;
    }

    public void fillRules(Accident accident, String[] rls) {
        List<Rule> rules = getRules();
        accident.setRules(new HashSet<>());
        for (int i = 0; i < rls.length; i++) {
                accident.getRules().add(rules.get(i));
        }
    }

}
