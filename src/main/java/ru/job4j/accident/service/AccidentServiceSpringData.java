package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.orm.AccidentRepositoryCrudAccident;
import ru.job4j.accident.repository.orm.AccidentRepositoryCrudRule;
import ru.job4j.accident.repository.orm.AccidentRepositoryCrudType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccidentServiceSpringData {
    private final AccidentRepositoryCrudAccident aStore;
    private final AccidentRepositoryCrudType tStore;
    private final AccidentRepositoryCrudRule rStore;


    public List<Accident> getAll() {
        return aStore.findAllAccidents();
    }

    public List<AccidentType> getTypes() {
        return tStore.getTypes();
    }

    public List<Rule> getRules() {
        return rStore.getRules();
    }

    @Transactional
    public void addAccident(Accident accident, String[] ids) {
        List<Rule> rules = getRules();
        Map<Integer, Rule> map = rules.stream().
                collect(Collectors.toMap(Rule::getId, Function.identity()));
        if (accident.getId() == 0) {
            for (String s : ids) {
                accident.getRules().add(map.get(Integer.parseInt(s)));
            }
            aStore.save(accident);
        } else {
            aStore.updateAccident(
                    accident.getName(),
                    accident.getText(),
                    accident.getAddress(),
                    accident.getId());
        }


    }

    /*@Transactional("update")
    public void update(Accident accident) {

    }*/

    public Accident findById(int id) {
        return aStore.getById(id);
    }

}


