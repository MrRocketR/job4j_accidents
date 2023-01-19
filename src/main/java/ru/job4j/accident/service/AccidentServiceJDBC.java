package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class AccidentServiceJDBC {
    private final AccidentJdbcTemplate store;

    public List<Rule> getRules() {
        return store.getRulesFromDB();
    }

    public List<AccidentType> getTypes() {
        return store.getTypesFromDB();
    }

    public List<Accident> showAccidents() {
        return store.show().stream().toList();
    }

    public Accident findById(int id) {
        return store.findById(id);
    }

    public void add(Accident accident, String[] ids) {
        store.insertNewAccident(accident, ids);
    }

    public void update(Accident accident, String[] ids) {
        store.update(accident, ids);
    }
}
