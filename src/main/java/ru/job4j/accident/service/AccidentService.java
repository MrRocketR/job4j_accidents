package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
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
        accidentRepository.add(accident);
    }
    public Collection<Accident> show() {
        Collection<Accident> accidents = accidentRepository.show();
        List<AccidentType> types = types();
        for (Accident a: accidents) {
            a.setType(types.get(a.getId()));
        }
        return accidents;
    }

    public Accident findById(int id) {
        return accidentRepository.findById(id);
    }
    public void update(Accident accident) {
        accidentRepository.update(accident);
    }
    public List<AccidentType> types() {
        List<AccidentType> types = new ArrayList<>();
        types.add(new AccidentType(1, "Две машины"));
        types.add(new AccidentType(2, "Машина и человек"));
        types.add(new AccidentType(3, "Машина и велосипед"));
        return types;
    }
}
