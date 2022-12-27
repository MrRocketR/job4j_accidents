package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.apache.tomcat.util.digester.Rules;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class AccidentServiceJDBC {
    private final AccidentJdbcTemplate accidentsRepostiory;

    public void create(Accident accident, String[] ids) {
        accidentsRepostiory.save(accident, ids);
    }

    public Collection<Accident> showALL() {
        return accidentsRepostiory.getAll();
    }

    public Collection<Rule> getRules() {
        return accidentsRepostiory.getRulesList();
    }

    public Collection<AccidentType> getTypes() {
        return accidentsRepostiory.getTypesList();
    }
}
