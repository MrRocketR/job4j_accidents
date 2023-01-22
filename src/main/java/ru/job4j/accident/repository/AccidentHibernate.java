package ru.job4j.accident.repository;

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
    private final SessionFactory sf;

    public Accident save(Accident accident, String[] ids) {
        List<Rule> rules = getRules();
        Map<Integer, Rule> map = rules.stream().
                collect(Collectors.toMap(Rule::getId,  Function.identity()));
        try (Session session = sf.openSession()) {
            for (String s : ids) {
               accident.getRules().add(map.get(Integer.parseInt(s)));
            }
            session.save(accident);
            return accident;
        }
    }

    public List<Accident> showAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Accident a join fetch a.type_id", Accident.class)
                    .list();
        }
    }

    public List<Rule> getRules() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from Rule", Rule.class)
                    .list();
        }
    }

    public List<AccidentType> getTypes() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from AccidentType", AccidentType.class)
                    .list();
        }
    }

    public void update(Accident accident, String[] ids) {
        List<Rule> rules = getRules();
        Map<Integer, Rule> map = rules.stream().
                collect(Collectors.toMap(Rule::getId,  Function.identity()));
        for (String s : ids) {
            accident.getRules().add(map.get(Integer.parseInt(s)));
        }
        try (Session session = sf.openSession()) {
            session.update(accident);
        }
    }


    public Accident findById(int id) {
        String sql = "from Accident a join fetch a.type_id where id = :fId";
        try (Session session = sf.openSession()) {
            Query<Accident> qAccident = session.createQuery(sql, Accident.class);
            qAccident.setParameter("fId", id);
            return qAccident.getSingleResult();
        }
    }

}
