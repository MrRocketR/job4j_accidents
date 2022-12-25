package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.List;


@Service
public class AccidentServiceHibernate {

    private final AccidentHibernate store;


    public AccidentServiceHibernate(AccidentHibernate store) {
        this.store = store;
    }

    public void create(Accident accident) {
        store.save(accident);
    }

    public List<Accident> getAll() {
        return store.getAll();
    }
}

