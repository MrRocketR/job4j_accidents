package ru.job4j.accident.repository.hiber;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class AccidentHibernate {

    private final CrudHibernate repo;
    private final RulesHibernate rulRepo;

    public void save(Accident accident, String[] ids) {
        List<Rule> rules = rulRepo.getRules();
        Map<Integer, Rule> map = rules.stream().
                collect(Collectors.toMap(Rule::getId, Function.identity()));
        for (String s : ids) {
            accident.getRules().add(map.get(Integer.parseInt(s)));
        }
        repo.run(session -> session.save(accident));
    }

    public List<Accident> showAll() {
        return repo.query("from Accident a join fetch a.type", Accident.class);
    }

    public Optional<Accident> findById(int id) {
        return repo.optional("from Accident a join fetch a.type where a.id =:fId", Accident.class,
                Map.of("fId", id));
    }

    public void update(int id, Accident accident) {
        repo.run("UPDATE Accident as a SET a.name =  :fName, a.text = :fText,  a.address = :fAddress"
                        + " WHERE  a.id = :fId",
                Map.of("fId", id,
                        "fName", accident.getName(),
                        "fText", accident.getText(),
                        "fAddress", accident.getAddress()));
    }
}



