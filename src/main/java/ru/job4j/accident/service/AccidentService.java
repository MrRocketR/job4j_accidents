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
        List<AccidentType> types = getTypes();
        AccidentType type = types.stream()
                .filter(at -> at.getId() == accident.getType()
                        .getId()).findFirst().get();
        accident.setType(type);
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

    public Map<Integer, Rule> getRules() {
        return accidentRepository.getRules();
    }



    public void fillRules(Accident accident, String[] rls) {
        accident.setRules(new HashSet<>());
        Stream.of(rls).map(Integer::valueOf)
                .forEach(i -> accident.getRules().add(getRules().get(i)));
    }

}
