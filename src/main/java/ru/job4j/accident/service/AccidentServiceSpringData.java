package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepositoryCrudAccident;
import ru.job4j.accident.repository.AccidentRepositoryCrudRule;
import ru.job4j.accident.repository.AccidentRepositoryCrudType;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccidentServiceSpringData {
    private final AccidentRepositoryCrudAccident accidentalStore;
    private final AccidentRepositoryCrudType typeStore;
    private final AccidentRepositoryCrudRule ruleStore;

    public void create(Accident accident) {
        accidentalStore.save(accident);
    }

    public List<Accident> getAll() {
        return accidentalStore.findAllAccidents();
    }

    public List<AccidentType> getTypes() {
        return typeStore.getTypes();
    }

    public List<Rule> getRules() {
        return ruleStore.getRules();
    }


    public void addAccident(Accident accident, String[] ids) {
        List<Rule> rules = getRules();
        Map<Integer, Rule> map = rules.stream().
                collect(Collectors.toMap(Rule::getId, Function.identity()));
        for (String s : ids) {
            accident.getRules().add(map.get(Integer.parseInt(s)));
        }
        accidentalStore.save(accident);
    }

    public void update(Accident accident, String[] ids) {
        List<Rule> rules = getRules();
        Map<Integer, Rule> map = rules.stream().
                collect(Collectors.toMap(Rule::getId, Function.identity()));
        for (String s : ids) {
            accident.getRules().add(map.get(Integer.parseInt(s)));

        }
        accidentalStore.updateAccident(
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getId());
    }

    public Accident findById(int id) {
        return accidentalStore.getById(id);
    }

}


