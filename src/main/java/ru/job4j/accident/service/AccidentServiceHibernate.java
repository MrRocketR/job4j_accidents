package ru.job4j.accident.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.hiber.AccidentHibernate;
import ru.job4j.accident.repository.hiber.RulesHibernate;
import ru.job4j.accident.repository.hiber.TypeHibernate;

import java.util.List;

@Service
@AllArgsConstructor
public class AccidentServiceHibernate {
    private final AccidentHibernate aStore;
    private final RulesHibernate rStore;
    private final TypeHibernate tStore;


    public List<Accident> showAccidents() {
        return aStore.showAll();
    }

    public Accident findById(int id) {
        return aStore.findById(id).get();
    }

    public void add(Accident accident, String[] ids) {
        aStore.save(accident, ids);
    }

    public void update(int id, Accident accident) {
        aStore.update(id, accident);
    }

    public List<Rule> getRules() {
        return rStore.getRules();
    }

    public List<AccidentType> getTypes() {
        return tStore.getTypes();
    }
}
